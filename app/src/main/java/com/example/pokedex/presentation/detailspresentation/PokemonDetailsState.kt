package com.example.pokedex.presentation.detailspresentation

import com.example.pokedex.domain.model.PokemonBasicInfoDomain
import com.example.pokedex.domain.model.PokemonDetailedInfoDomain

data class PokemonDetailsState(
    val pokemonDetails: PokemonDetailedInfoDomain = PokemonDetailedInfoDomain(
        pokemonBasicInfoDomain =  PokemonBasicInfoDomain(
            id = 0,
            name = "",
            weight = 0,
            height =  0,
            presentationImageUrl = "",
            threeDModelUrl = ""
        ),
        cries = null,
        species = null,
        abilities = emptyList(),
        stats = emptyList(),
        types = emptyList(),
    )
)
