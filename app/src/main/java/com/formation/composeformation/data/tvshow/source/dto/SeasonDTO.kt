package com.formation.composeformation.data.tvshow.source.dto

import com.formation.composeformation.data.mapper.Mapper
import com.formation.composeformation.data.utils.extension.InputDatePattern
import com.formation.composeformation.data.utils.extension.buildTMDBImagePath
import com.formation.composeformation.data.utils.extension.toLocalDate
import com.formation.composeformation.domain.tvshow.model.SeasonDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDTO(
    @SerialName("air_date") val airDate: String?,
    @SerialName("episode_count") val episodeCount: Int,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("vote_average") val voteAverage: Double,
)

//==================================================================================================
// Mapper
//==================================================================================================
class SeasonDetailMapper : Mapper<SeasonDomain, SeasonDTO> {

    override fun convert(input: SeasonDTO): SeasonDomain {
        return SeasonDomain(
            id = input.id,
            name = input.name ?: "",
            overview = input.overview ?: "",
            posterPath = input.posterPath.buildTMDBImagePath(),
            seasonNumber = input.seasonNumber,
            voteAverage = input.voteAverage,
            airDate = input.airDate.toLocalDate(pattern = InputDatePattern.YearMonthDayFormat),
            episodeCount = input.episodeCount
        )
    }
}

//==================================================================================================
// Mapper Extensions
//==================================================================================================
fun List<SeasonDTO>?.convert(): List<SeasonDomain> {
    return SeasonDetailMapper().convertOrEmpty(this)
}
