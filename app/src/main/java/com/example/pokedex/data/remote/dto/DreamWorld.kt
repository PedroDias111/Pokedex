package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DreamWorld(
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("front_female") val frontFemale: String?
)