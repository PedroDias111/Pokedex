package com.example.pokedex.domain.model

data class PokemonList (
    val results: List<Result>
)

data class Result(
    val name: String?,
    val url: String?
)
