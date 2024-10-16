package com.example.pokedex.data.remote

import com.example.myapplication.data.remote.dto.PokemonInfoDto
import com.example.myapplication.data.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): PokemonInfoDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}