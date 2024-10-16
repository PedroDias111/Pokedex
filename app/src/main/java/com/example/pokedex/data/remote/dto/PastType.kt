package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastType(
    @SerialName("generation") val generation: Generation,
    @SerialName("types") val types: List<TypeDto>
)