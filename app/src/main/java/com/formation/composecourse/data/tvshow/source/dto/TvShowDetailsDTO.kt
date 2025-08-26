package com.formation.composecourse.data.tvshow.source.dto

import com.formation.composecourse.data.mapper.Mapper
import com.formation.composecourse.data.movie.source.remote.dto.GenreDTO
import com.formation.composecourse.data.movie.source.remote.dto.ProductionCompanyDTO
import com.formation.composecourse.data.movie.source.remote.dto.ProductionCountryDTO
import com.formation.composecourse.data.movie.source.remote.dto.SpokenLanguageDTO
import com.formation.composecourse.data.utils.extension.InputDatePattern
import com.formation.composecourse.data.utils.extension.buildTMDBBackdropImagePath
import com.formation.composecourse.data.utils.extension.buildTMDBImagePath
import com.formation.composecourse.data.utils.extension.toLocalDate
import com.formation.composecourse.domain.tvshow.model.TvShowDetailsDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDetailsDTO(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("created_by") val createdBy: List<CreatedByDTO>,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>?,
    @SerialName("first_air_date") val firstAirDate: String?,
    @SerialName("genres") val genres: List<GenreDTO>,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int,
    @SerialName("in_production") val inProduction: Boolean,
    @SerialName("languages") val languages: List<String>?,
    @SerialName("last_air_date") val lastAirDate: String?,
    @SerialName("last_episode_to_air") val lastEpisodeToAir: LastEpisodeToAirDTO?,
    @SerialName("name") val name: String?,
    //@SerialName("next_episode_to_air") val nextEpisodeToAir: String?,
    @SerialName("networks") val networks: List<NetworksDTO>,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int,
    @SerialName("number_of_seasons") val numberOfSeasons: Int,
    @SerialName("origin_country") val originCountry: List<String>?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("original_name") val originalName: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyDTO>?,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryDTO>?,
    @SerialName("seasons") val seasons: List<SeasonDTO>?,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageDTO>?,
    @SerialName("status") val status: String?,
    @SerialName("tagline") val tagline: String?,
    @SerialName("type") val type: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)

//==================================================================================================
// Mapper
//==================================================================================================
class TvShowDetailMapper : Mapper<TvShowDetailsDomain, TvShowDetailsDTO> {

    override fun convert(input: TvShowDetailsDTO): TvShowDetailsDomain {
        return TvShowDetailsDomain(
            id = input.id,
            name = input.name ?: "",
            firstAirDate = input.firstAirDate.toLocalDate(pattern = InputDatePattern.YearMonthDayFormat),
            overview = input.overview ?: "",
            backdropPath = input.backdropPath.buildTMDBBackdropImagePath() ?: "",
            posterPath = input.posterPath.buildTMDBImagePath(),
            voteAverage = input.voteAverage,
            seasons = input.seasons.convert()
        )
    }
}

//==================================================================================================
// Mapper Extensions
//==================================================================================================
fun TvShowDetailsDTO.convert(): TvShowDetailsDomain {
    return TvShowDetailMapper().convert(this)
}
