package com.formation.composecourse.data.search.source.remote.dto

import com.formation.composecourse.data.mapper.Mapper
import com.formation.composecourse.data.utils.extension.InputDatePattern
import com.formation.composecourse.data.utils.extension.buildTMDBImagePath
import com.formation.composecourse.data.utils.extension.toLocalDate
import com.formation.composecourse.domain.search.model.MediaType
import com.formation.composecourse.domain.search.model.SearchDataDomain
import com.formation.composecourse.domain.search.model.SearchDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class SearchDataDTO(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<SearchDTO>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)

@Serializable
data class SearchDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("media_type") val mediaType: String? = null,
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("genre_ids") val genreIds: List<Int>? = null,
    @SerialName("first_air_date") val firstAirDate: String? = null,
    @SerialName("origin_country") val originCountry: List<String>? = null,
    @SerialName("vote_count") val voteCount: Int? = null
)

//==================================================================================================
// Mapper
//==================================================================================================
class SearchDataMapper : Mapper<SearchDataDomain, SearchDataDTO> {

    override fun convert(input: SearchDataDTO): SearchDataDomain {
        return SearchDataDomain(
            page = input.page,
            results = input.results.convertOrEmpty(),
            totalPages = input.totalPages,
            totalResults = input.totalResults
        )
    }
}

class SearchMapper : Mapper<SearchDomain, SearchDTO> {

    override fun convert(input: SearchDTO): SearchDomain {
        return SearchDomain(
            id = input.id,
            name = input.name ?: "",
            title = input.title ?: "",
            overview = input.overview ?: "",
            releaseDate = input.releaseDate?.toLocalDate(pattern = InputDatePattern.YearMonthDayFormat)
                ?: LocalDate.now(),
            posterPath = input.posterPath?.buildTMDBImagePath() ?: "",
            profilePath = input.profilePath?.buildTMDBImagePath() ?: "",
            voteAverage = (input.voteAverage ?: 0.0) * 10,
            mediaType = MediaType.fromValue(input.mediaType)
        )
    }
}

//==================================================================================================
// Mapper Extensions
//==================================================================================================
fun SearchDataDTO.convert(): SearchDataDomain {
    return SearchDataMapper().convert(this)
}

fun List<SearchDTO>.convertOrEmpty(): List<SearchDomain> {
    return SearchMapper().convert(this)
}
