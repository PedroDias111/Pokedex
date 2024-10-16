package com.example.myapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at") val levelLearnedAt: Int,
    @SerialName("version_group") val moveLearnMethod: MoveLearnMethod,
    @SerialName("move_learn_method") val versionGroup: VersionGroup
)