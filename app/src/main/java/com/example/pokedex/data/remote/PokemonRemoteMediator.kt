package com.example.pokedex.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.local.PokemonDetailedInfoEntity
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.myapplication.util.Constants.PAGE_SIZE
import com.example.pokedex.data.local.RemoteKeys
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
    private val api: PokeAPI,
    private val db: PokemonDatabase,
    private val repository: PokemonRepository
) : RemoteMediator<Int, PokemonDetailedInfoEntity>(){

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonDetailedInfoEntity>
    ): MediatorResult {
        val limit = PAGE_SIZE
        val initialPage = 0

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: initialPage
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val results = repository.getPokemonList(limit = limit, offset = page * limit)

            val endOfPaginationReached = results.isEmpty()

            db.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeys()
                    db.dao.clearPokemonDetailedInfoEntity()
                }
                val prevKey = if (page == initialPage) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = results.map {
                    RemoteKeys(id = it.pokemonBasicInfoEntity.id, prevKey = prevKey, nextKey = nextKey)
                }
                results.map{ pokemon->

                    // Upsert the data into the database
                    pokemon.species?.let {species ->
                        pokemon.cries?.let { cries ->
                            db.dao.upsertPokemonDetailedInfoEntity(
                                pokemon = pokemon.pokemonBasicInfoEntity,
                                cries = cries,
                                species = species,
                                stats = pokemon.stats,
                                types = pokemon.types,
                                abilities = pokemon.abilities
                            )
                        }
                    }
                }
                db.remoteKeysDao().insertAllRemoteKeys(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PokemonDetailedInfoEntity>
    ): RemoteKeys? {

        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemon ->
                db.remoteKeysDao().getRemoteKeysByPokemonName(id = pokemon.pokemonBasicInfoEntity.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonDetailedInfoEntity>
    ): RemoteKeys? {

        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.pokemonBasicInfoEntity?.id?.let { id ->
                db.remoteKeysDao().getRemoteKeysByPokemonName(id = id)
            }
        }
    }
}