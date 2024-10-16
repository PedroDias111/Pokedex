package com.example.pokedex.data.mapper

import com.example.myapplication.data.remote.dto.PokemonListDto
import com.example.pokedex.domain.model.PokemonList
import com.example.pokedex.domain.model.Result

fun PokemonListDto.toPokemonList() = PokemonList(
    results = results.map { result->
        Result(
            name = result.name,
            url = result.url
        )
    }
)




