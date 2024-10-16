package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?
)