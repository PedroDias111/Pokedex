package com.example.pokedex.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.local.PokemonDetailedInfoEntity
import com.example.pokedex.data.mapper.toAbilityEntity
import com.example.pokedex.data.mapper.toCriesEntity
import com.example.pokedex.data.mapper.toTypeEntity
import com.example.pokedex.data.mapper.toPokemonBasicInfoEntity
import com.example.pokedex.data.mapper.toSpeciesEntity
import com.example.pokedex.data.mapper.toStatEntity
import com.example.pokedex.data.remote.PokeAPI
import com.example.pokedex.domain.model.PokemonDetailedInfoDomain
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.data.mapper.toPokemonInfo
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImplementation @Inject constructor(
    private val api: PokeAPI,
    private val db: PokemonDatabase,
): PokemonRepository {

    private val dao = db.dao

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): List<PokemonDetailedInfoEntity> {
        val detailedPokemonList = mutableListOf<PokemonDetailedInfoEntity>()

        return try {
            val response = api.getPokemonList(limit = limit, offset = offset)
            response.results.forEach { pokemon ->
                pokemon.name?.let {pokemonName ->
                    val pokemonDetailedInfoResponse = getPokemonInfo(pokemonName)
                    // Append the detailed info to the list
                    detailedPokemonList.add(pokemonDetailedInfoResponse)
                }
            }
            return  detailedPokemonList.toList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getPokemonInfo(
        pokemonName: String
    ): PokemonDetailedInfoEntity {

            // Fetch the Pokémon details from the API
            val response = api.getPokemonInfo(name = pokemonName)

            // Ensure required fields are not null, or provide default handling
            val pokemonBasicInfoEntity = response.toPokemonBasicInfoEntity()

            val criesEntity = response.cries.toCriesEntity(response.id)

            val speciesEntity = response.species.toSpeciesEntity(response.id)

            val statsEntities = response.stats.map { statDto ->
                statDto.toStatEntity(response.id)
            }
            val typeEntities = response.types.map { typeDto ->
                typeDto.toTypeEntity(response.id)
            }
            val abilitiesEntities = response.abilities.map { abilityDto ->
                abilityDto.toAbilityEntity(response.id)
            }

            // Return the mapped Pokémon info domain object
            return PokemonDetailedInfoEntity(
                    pokemonBasicInfoEntity= pokemonBasicInfoEntity,
                    cries= criesEntity,
                    species= speciesEntity,
                    abilities= abilitiesEntities,
                    stats= statsEntities,
                    types = typeEntities
            )
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPokemonInfoScreen(
        pokemonName: String
    ): Result<PokemonDetailedInfoDomain> {

        try {
            // Fetch the Pokémon details from the API
            val response = api.getPokemonInfo(name = pokemonName)

            // Ensure required fields are not null, or provide default handling
            val pokemonBasicInfoEntity = response.toPokemonBasicInfoEntity()

            val criesEntity = response.cries.toCriesEntity(response.id)

            val speciesEntity = response.species.toSpeciesEntity(response.id)

            val statsEntities = response.stats.map { statDto ->
                statDto.toStatEntity(response.id)
            }
            val typeEntities = response.types.map { typeDto ->
                typeDto.toTypeEntity(response.id)
            }
            val abilitiesEntities = response.abilities.map { abilityDto ->
                abilityDto.toAbilityEntity(response.id)
            }

            // Return the mapped Pokémon info domain object
            return Result.success(
                PokemonDetailedInfoEntity(
                    pokemonBasicInfoEntity= pokemonBasicInfoEntity,
                    cries= criesEntity,
                    species= speciesEntity,
                    abilities= abilitiesEntities,
                    stats= statsEntities,
                    types = typeEntities
                ).toPokemonInfo())
        }catch (e: IOException) {
            // Handle network-related issues and return failure
            return  Result.failure(e)
        } catch (e: HttpException) {
            // Handle HTTP errors and return failure
            return  Result.failure(e)
        } catch (e: Exception) {
            // Catch any other unexpected exceptions and return failure
            return  Result.failure(e)
        }
    }

    override fun getPagingSource(): PagingSource<Int, PokemonDetailedInfoEntity> {
        return dao.pagingSource()
    }
}



