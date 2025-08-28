package com.formation.composecourse.data.movie.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDTO(
    @SerialName("iso_3166_1") val iso: String,
    @SerialName("name") val name: String
)
