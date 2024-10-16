package com.example.pokedex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonListElementEntity(
    @PrimaryKey val name: String,
    val url: String,
)
