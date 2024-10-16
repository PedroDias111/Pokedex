package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CriesDto(
    @SerialName("latest") val latest: String?,
    @SerialName("legacy") val legacy: String?
)