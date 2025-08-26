package com.formation.composecourse.domain.search.model

import java.time.LocalDate

enum class MediaType(val value: String) {
    Tv("tv"),
    Movie("movie"),
    Person("person"),
    Unknown("");

    companion object {
        fun fromValue(value: String?): MediaType {
            return entries.firstOrNull { it.value == value } ?: Unknown
        }
    }
}

data class SearchDataDomain(
    val page: Int,
    val results: List<SearchDomain>,
    val totalPages: Int,
    val totalResults: Int
)

data class SearchDomain(
    val id: Int,
    val name: String,
    val title: String,
    val overview: String,
    val releaseDate: LocalDate,
    val posterPath: String,
    val profilePath: String,
    val voteAverage: Double,
    val mediaType: MediaType
)
