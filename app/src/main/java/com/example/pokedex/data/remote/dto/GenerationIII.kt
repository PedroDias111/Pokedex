package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIII(
    @SerialName("emerald") val emerald: Emerald,
    @SerialName("firered-leafgreen") val fireRedLeafGreen: FireRedLeafGreen,
    @SerialName("ruby-sapphire") val rubySapphire: RubySapphire
)