package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVI(
    @SerialName("omegaruby-alphasapphire") val omegaRubyAlphaSapphire: OmegaRubyAlphaSapphire,
    @SerialName("x-y") val xy: XY
)