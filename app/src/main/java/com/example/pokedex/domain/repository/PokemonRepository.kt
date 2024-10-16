package com.example.pokedex.domain.repository

import androidx.paging.PagingSource
import com.example.pokedex.data.local.PokemonDetailedInfoEntity
import com.example.pokedex.domain.model.PokemonDetailedInfoDomain

interface PokemonRepository {

    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): List<PokemonDetailedInfoEntity>

    suspend fun getPokemonInfo(
        pokemonName: String
    ): PokemonDetailedInfoEntity

    suspend fun getPokemonInfoScreen(
        pokemonName: String
    ): Result<PokemonDetailedInfoDomain>

    fun getPagingSource(): PagingSource<Int, PokemonDetailedInfoEntity>
}