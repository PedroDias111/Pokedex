package com.example.pokedex.domain.model

data class PokemonDetailedInfoDomain(
    // Non primitive types of data so room flatten their properties into the same table as the entity that references them
    val pokemonBasicInfoDomain: PokemonBasicInfoDomain,
    val cries: CriesDomain?,  // Nullable to handle cases without associated cries
    val species: SpeciesDomain?,  // Nullable for the same reason
    val abilities: List<AbilityDomain>,
    val stats: List<StatDomain>,
    val types: List<TypeDomain>,
)

data class PokemonBasicInfoDomain(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val presentationImageUrl: String,
    val threeDModelUrl: String,
)

data class CriesDomain(
    val latest: String?, // The latest depiction of this Pokémon's cry.
)

data class SpeciesDomain(
    val name: String?,
    val url: String?
)

data class StatDomain(
    val name: String?,
    val baseStat: Int,
)

data class TypeDomain(
    val name: String?,
)

data class AbilityDomain(
    val name: String?,
    val isHidden: Boolean,
    val url: String?,
)