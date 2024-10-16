package com.example.pokedex.navigation

import kotlinx.serialization.Serializable

internal sealed class Screens {

    @Serializable
    object PokemonListScreen

    @Serializable
    data class PokemonDetailsScreen(val name: String)
}