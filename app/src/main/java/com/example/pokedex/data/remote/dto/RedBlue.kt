package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedBlue(
    @SerialName("back_default") val backDefault: String?,
    @SerialName("back_gray") val backGray: String?,
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("front_gray") val frontGray: String?
)