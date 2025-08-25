package com.formation.composeformation.domain.movie.model

import java.time.LocalDate

data class MovieDetailsDomain(
    val id: Int,
    val posterPath: String,
    val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreDomain>,
    val title: String,
    val overview: String,
    val releaseDate: LocalDate?,
    val website: String?
)

data class GenreDomain(
    val id: Int,
    val name: String
)
