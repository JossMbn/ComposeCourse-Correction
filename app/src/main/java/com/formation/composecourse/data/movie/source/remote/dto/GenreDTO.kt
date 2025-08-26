package com.formation.composecourse.data.movie.source.remote.dto

import com.formation.composecourse.data.mapper.Mapper
import com.formation.composecourse.domain.movie.model.GenreDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String?
)

//==================================================================================================
// Mapper
//==================================================================================================
class GenreMapper : Mapper<GenreDomain, GenreDTO> {
    override fun convert(input: GenreDTO): GenreDomain {
        return GenreDomain(
            id = input.id,
            name = input.name ?: ""
        )
    }
}

//==================================================================================================
// Mapper Extensions
//==================================================================================================
fun List<GenreDTO>.convertOrEmpty(): List<GenreDomain> {
    return GenreMapper().convertOrEmpty(this)
}
