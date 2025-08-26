package com.formation.composecourse.domain.tvshow.model

import java.time.LocalDate

data class TvShowDetailsDomain(
    val id: Int,
    val name: String,
    val firstAirDate: LocalDate,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double,
    val seasons: List<SeasonDomain>
)
