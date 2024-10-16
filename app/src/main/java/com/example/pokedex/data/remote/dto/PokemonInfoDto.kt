package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class PokemonInfoDto(
    @SerialName("abilities") val abilities: List<AbilityDto>,
    @SerialName("base_experience") val baseExperience: Int,
    @SerialName("cries") val cries: CriesDto,
    @SerialName("forms") val forms: List<Form>,
    @SerialName("game_indices") val gameIndices: List<GameIndice>,
    @SerialName("height") val height: Int,
    @SerialName("held_items") val heldItems: List<HeldItem>,
    @SerialName("id") val id: Int,
    @SerialName("is_default") val isDefault: Boolean,
    @SerialName("location_area_encounters") val locationAreaEncounters: String,
    @SerialName("moves") val moves: List<Move>,
    @SerialName("name") val name: String,
    @SerialName("order") val order: Int,
    @SerialName("past_types") val pastTypes: List<PastType>,
    @SerialName("species") val species: SpeciesDto,
    @SerialName("sprites") val sprites: Sprites,
    @SerialName("stats") val stats: List<StatDto>,
    @SerialName("types") val types: List<TypeDto>,
    @SerialName("weight") val weight: Int
)