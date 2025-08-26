package com.formation.composecourse.data.tvshow.source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedByDTO(
    @SerialName("id") val id: Int,
    @SerialName("credit_id") val creditId: String?,
    @SerialName("name") val name: String?,
    @SerialName("gender") val gender: Int,
    @SerialName("profile_path") val profilePath: String?
)
