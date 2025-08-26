package com.formation.composecourse.data.tvshow.source.dto

import com.formation.composecourse.data.mapper.Mapper
import com.formation.composecourse.data.utils.extension.InputDatePattern
import com.formation.composecourse.data.utils.extension.buildTMDBImagePath
import com.formation.composecourse.data.utils.extension.toLocalDate
import com.formation.composecourse.domain.tvshow.model.TvShowDataDomain
import com.formation.composecourse.domain.tvshow.model.TvShowDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDataDTO(
    val page: Int,
    val results: List<TvShowDTO>
)

@Serializable
data class TvShowDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val title: String,
    @SerialName("first_air_date") val releaseDate: String,
    @SerialName("overview") val overview: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("popularity") val popularity: Double
)

//==================================================================================================
// Mapper
//==================================================================================================
class TvShowDataMapper : Mapper<TvShowDataDomain, TvShowDataDTO> {

    override fun convert(input: TvShowDataDTO): TvShowDataDomain {
        return TvShowDataDomain(
            page = input.page,
            results = input.results.convertOrEmpty()
        )
    }
}

class TvShowMapper : Mapper<TvShowDomain, TvShowDTO> {

    override fun convert(input: TvShowDTO): TvShowDomain {
        return TvShowDomain(
            id = input.id,
            name = input.title,
            firstAirDate = input.releaseDate.toLocalDate(pattern = InputDatePattern.YearMonthDayFormat),
            overview = input.overview,
            backdropPath = input.backdropPath.buildTMDBImagePath(),
            posterPath = input.posterPath.buildTMDBImagePath(),
            voteAverage = input.voteAverage * 10,
            popularity = input.popularity
        )
    }
}

//==================================================================================================
// Mapper Extensions
//==================================================================================================
fun TvShowDataDTO.convert(): TvShowDataDomain {
    return TvShowDataMapper().convert(this)
}

fun List<TvShowDTO>.convertOrEmpty(): List<TvShowDomain> {
    return TvShowMapper().convert(this)
}
