package com.formation.composeformation.data.movie.source.remote.dto

import com.formation.composeformation.data.mapper.Mapper
import com.formation.composeformation.data.utils.extension.buildTMDBImagePath
import com.formation.composeformation.domain.movie.model.CreditDepartment
import com.formation.composeformation.domain.movie.model.CreditDomain
import com.formation.composeformation.domain.movie.model.MovieCreditsDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsDTO(
    @SerialName("id") val id: Int,
    @SerialName("cast") val cast: List<CreditDTO>?
)

@Serializable
data class CreditDTO(
    @SerialName("adult") val adult: Boolean,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String?,
    @SerialName("name") val name: String?,
    @SerialName("original_name") val originalName: String?,
    @SerialName("popularity") val popularity: Double,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("cast_id") val castId: Int,
    @SerialName("character") val character: String?,
    @SerialName("credit_id") val creditId: String?,
    @SerialName("order") val order: Int
)

class MovieCreditsMapper : Mapper<MovieCreditsDomain, MovieCreditsDTO> {

    override fun convert(input: MovieCreditsDTO) = MovieCreditsDomain(
        id = input.id,
        cast = input.cast.convert()
            .filter { !it.profilePath.isNullOrEmpty() && !it.name.isNullOrEmpty() }
    )
}

fun MovieCreditsDTO.convert(): MovieCreditsDomain {
    return MovieCreditsMapper().convert(this)
}

class CreditMapper : Mapper<CreditDomain, CreditDTO> {

    override fun convert(input: CreditDTO): CreditDomain = CreditDomain(
        id = input.id,
        department = CreditDepartment.fromString(input.knownForDepartment),
        character = input.character ?: "",
        name = input.name,
        profilePath = input.profilePath.buildTMDBImagePath()
    )
}

fun List<CreditDTO>?.convert(): List<CreditDomain> {
    return CreditMapper().convertOrEmpty(this)
}
