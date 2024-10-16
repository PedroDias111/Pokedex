package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIV(
    @SerialName("diamond-pearl") val diamondPearl: DiamondPearl,
    @SerialName("heartgold-soulsilver") val heartGoldSoulSilver: HeartGoldSoulSilver,
    @SerialName("platinum") val platinum: Platinum
)