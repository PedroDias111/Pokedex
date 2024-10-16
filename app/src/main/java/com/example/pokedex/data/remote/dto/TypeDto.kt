package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeDto(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val type: TypeX
)