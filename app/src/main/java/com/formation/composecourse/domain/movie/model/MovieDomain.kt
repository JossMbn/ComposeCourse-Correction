package com.formation.composecourse.domain.movie.model

import com.formation.composecourse.domain.utils.extension.PresentationDateTimePattern
import com.formation.composecourse.domain.utils.extension.toFormattedString
import java.time.LocalDate

data class MovieDataDomain(
    val page: Int,
    val results: List<MovieDomain>
)

data class MovieDomain(
    val id: Int,
    val title: String,
    val releaseDate: LocalDate,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double,
    val popularity: Double
) {
    val formattedReleaseDate: String
        get() = releaseDate.toFormattedString(PresentationDateTimePattern.DayMonthYearShortFormat)
}
