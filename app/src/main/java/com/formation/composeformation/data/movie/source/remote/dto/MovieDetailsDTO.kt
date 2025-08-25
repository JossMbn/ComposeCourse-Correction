package com.formation.composeformation.data.movie.source.remote.dto

import com.formation.composeformation.data.mapper.Mapper
import com.formation.composeformation.data.utils.extension.InputDatePattern
import com.formation.composeformation.data.utils.extension.buildTMDBBackdropImagePath
import com.formation.composeformation.data.utils.extension.buildTMDBImagePath
import com.formation.composeformation.data.utils.extension.toLocalDate
import com.formation.composeformation.domain.movie.model.MovieDetailsDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDTO(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("budget") val budget: Int,
    @SerialName("genres") val genres: List<GenreDTO>,
    @SerialName("homepage") val homepage: String,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyDTO>?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("revenue") val revenue: Int,
    @SerialName("runtime") val runtime: Int,
    @SerialName("spoken_languages") val spokenLanguageDTOS: List<SpokenLanguageDTO>,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("title") val title: String,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)

//==================================================================================================
// Mapper
//==================================================================================================
class MovieDetailMapper : Mapper<MovieDetailsDomain, MovieDetailsDTO> {

    override fun convert(input: MovieDetailsDTO): MovieDetailsDomain {
        return MovieDetailsDomain(
            id = input.id,
            posterPath = input.posterPath.buildTMDBImagePath(),
            backdropPath = input.backdropPath.buildTMDBBackdropImagePath(),
            budget = input.budget,
            genres = input.genres.convertOrEmpty(),
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate.toLocalDate(),
            website = input.homepage
        )
    }
}

//==================================================================================================
// Mapper Extensions
//==================================================================================================
fun MovieDetailsDTO.convert(): MovieDetailsDomain {
    return MovieDetailMapper().convert(this)
}
