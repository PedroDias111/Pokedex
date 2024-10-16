package com.example.pokedex.data.mapper

import com.example.pokedex.data.local.AbilityEntity
import com.example.pokedex.data.local.CriesEntity
import com.example.pokedex.data.local.PokemonBasicInfoEntity
import com.example.pokedex.data.local.PokemonDetailedInfoEntity
import com.example.pokedex.data.local.SpeciesEntity
import com.example.pokedex.data.local.StatEntity
import com.example.pokedex.data.local.TypeEntity
import com.example.myapplication.data.remote.dto.AbilityDto
import com.example.myapplication.data.remote.dto.CriesDto
import com.example.myapplication.data.remote.dto.PokemonInfoDto
import com.example.myapplication.data.remote.dto.SpeciesDto
import com.example.myapplication.data.remote.dto.StatDto
import com.example.myapplication.data.remote.dto.TypeDto
import com.example.pokedex.domain.model.AbilityDomain
import com.example.pokedex.domain.model.CriesDomain
import com.example.pokedex.domain.model.PokemonBasicInfoDomain
import com.example.pokedex.domain.model.PokemonDetailedInfoDomain
import com.example.pokedex.domain.model.SpeciesDomain
import com.example.pokedex.domain.model.StatDomain
import com.example.pokedex.domain.model.TypeDomain

fun PokemonInfoDto.toPokemonInfo() = PokemonDetailedInfoDomain(
    pokemonBasicInfoDomain = PokemonBasicInfoDomain(
        id = id,
        name = name,
        weight = weight,
        height = height,
        presentationImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
        threeDModelUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/${id}.gif"
    ),

    cries = CriesDomain(
        latest = cries.latest,
    ),

    species = SpeciesDomain(
        name = species.name,
        url = species.url
    ),

    abilities = abilities.map{ ability->
        AbilityDomain(
            name = ability.ability.name,
            url = ability.ability.url,
            isHidden = ability.isHidden,
        )
    },

    stats = stats.map{ stat->
        StatDomain(
            name = stat.stat.name,
            baseStat = stat.baseStat,
        )
    },

    types = types.map{ type->
        TypeDomain(
            name = type.type.name,
        )
    },
)

fun PokemonInfoDto.toPokemonBasicInfoEntity() = PokemonBasicInfoEntity(
        id = id,
        name = name,
        weight = weight,
        height = height,
        presentationImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
        threeDModelUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/${id}.gif"
)

fun CriesDto.toCriesEntity(pokemonId: Int): CriesEntity {
    return CriesEntity(
        latest = latest ?: throw IllegalArgumentException("Latest cry is missing"),
        pokemonId = pokemonId
    )
}

fun SpeciesDto.toSpeciesEntity(pokemonId: Int): SpeciesEntity {
    return SpeciesEntity(
        pokemonId = pokemonId,
        name = name ?: throw IllegalArgumentException("Species name is missing"),
        url = url ?: throw IllegalArgumentException("Species URL is missing")
    )
}

fun StatDto.toStatEntity(pokemonId: Int): StatEntity {
    return StatEntity(
        pokemonId = pokemonId,
        name = stat.name ?: throw IllegalArgumentException("Stat name is missing"),
        baseStat = baseStat
    )
}

fun TypeDto.toTypeEntity(pokemonId: Int): TypeEntity {
    return TypeEntity(
        pokemonId = pokemonId,
        name = type.name ?: throw IllegalArgumentException("Type name is missing")
    )
}

fun AbilityDto.toAbilityEntity(pokemonId: Int): AbilityEntity {
    return AbilityEntity(
        name = ability.name ?: throw IllegalArgumentException("Ability name is missing"),
        isHidden = isHidden,
        url = ability.url ?: throw IllegalArgumentException("Ability URL is missing"),
        pokemonId = pokemonId
    )
}


fun PokemonDetailedInfoEntity.toPokemonInfo() = PokemonDetailedInfoDomain(
    pokemonBasicInfoDomain = pokemonBasicInfoEntity.toPokemonBasicInfoDomain(),
    cries = cries?.toCriesDomain(),
    species = species?.toSpeciesDomain(),
    abilities = abilities.map { it.toAbilityDomain()},
    stats = stats.map { it.toStatDomain()},
    types = types.map { it.toTypeDomain()}
)

fun PokemonBasicInfoEntity.toPokemonBasicInfoDomain() = PokemonBasicInfoDomain(
    id = id,
    name = name,
    weight = weight,
    height = height,
    presentationImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
    threeDModelUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/${id}.gif"
)

private fun CriesEntity.toCriesDomain() = CriesDomain(
    latest = latest
)

private fun SpeciesEntity.toSpeciesDomain() = SpeciesDomain(
    name = name,
    url = url
)

private fun StatEntity.toStatDomain() = StatDomain(
    name = name,
    baseStat = baseStat
)

private fun TypeEntity.toTypeDomain() = TypeDomain(
    name = name,
)

private fun AbilityEntity.toAbilityDomain() = AbilityDomain(
    name = name,
    isHidden = isHidden,
    url = url,
)



