package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationII(
    @SerialName("crystal") val crystal: Crystal,
    @SerialName("gold") val gold: Gold,
    @SerialName("silver") val silver: Silver
)