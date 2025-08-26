package com.formation.composecourse.data.tvshow.source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworksDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String?,
    @SerialName("logo_path") val logoPath: String?,
    @SerialName("origin_country") val originCountry: String?
)
