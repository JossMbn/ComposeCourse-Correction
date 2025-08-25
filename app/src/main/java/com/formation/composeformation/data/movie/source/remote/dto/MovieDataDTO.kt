package com.formation.composeformation.data.movie.source.remote.dto

import com.formation.composeformation.data.mapper.Mapper
import com.formation.composeformation.data.utils.extension.InputDatePattern
import com.formation.composeformation.data.utils.extension.buildTMDBImagePath
import com.formation.composeformation.data.utils.extension.toLocalDate
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.movie.model.MovieDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataDTO(
    val page: Int,
    val results: List<MovieDTO>
)

@Serializable
data class MovieDTO(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("overview") val overview: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("popularity") val popularity: Double
)

//==================================================================================================
// Mapper
//==================================================================================================
class MovieDataMapper : Mapper<MovieDataDomain, MovieDataDTO> {

    override fun convert(input: MovieDataDTO): MovieDataDomain {
        return MovieDataDomain(
            page = input.page,
            results = input.results.convertOrEmpty()
        )
    }
}

class MovieMapper : Mapper<MovieDomain, MovieDTO> {

    override fun convert(input: MovieDTO): MovieDomain {
        return MovieDomain(
            id = input.id,
            title = input.title,
            releaseDate = input.releaseDate.toLocalDate(pattern = InputDatePattern.YearMonthDayFormat),
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
fun MovieDataDTO.convert(): MovieDataDomain {
    return MovieDataMapper().convert(this)
}

fun List<MovieDTO>.convertOrEmpty(): List<MovieDomain> {
    return MovieMapper().convert(this)
}
