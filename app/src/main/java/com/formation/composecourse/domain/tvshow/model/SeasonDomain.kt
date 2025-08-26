package com.formation.composecourse.domain.tvshow.model

import java.time.LocalDate

data class SeasonDomain(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
    val voteAverage: Double,
    val airDate: LocalDate,
    val episodeCount: Int
)
