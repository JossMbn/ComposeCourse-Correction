package com.formation.composeformation.domain.tvshow.model

import com.formation.composeformation.domain.utils.extension.PresentationDateTimePattern
import com.formation.composeformation.domain.utils.extension.toFormattedString
import java.time.LocalDate

data class TvShowDataDomain(
    val page: Int,
    val results: List<TvShowDomain>
)

data class TvShowDomain(
    val id: Int,
    val name: String,
    val firstAirDate: LocalDate,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Double,
    val popularity: Double
) {
    val formattedReleaseDate: String
        get() = firstAirDate.toFormattedString(PresentationDateTimePattern.DayMonthYearShortFormat)
}
