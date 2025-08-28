package com.formation.composecourse.data.movie.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageDTO(
    @SerialName("english_name") val englishName: String,
    @SerialName("iso_639_1") val iso: String,
    @SerialName("name") val name: String
)
