package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityDto(
    @SerialName("ability") val ability: AbilityX,
    @SerialName("is_hidden") val isHidden: Boolean,
    @SerialName("slot") val slot: Int
)