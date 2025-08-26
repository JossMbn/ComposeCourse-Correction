package com.formation.composecourse.data.movie.source.remote.endpoint

import com.formation.composecourse.data.movie.source.remote.dto.MovieDetailsDTO
import com.formation.composecourse.data.movie.source.remote.dto.convert
import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.movie.model.MovieDetailsDomain
import com.formation.composecourse.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetMovieDetailsEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        movieId: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<MovieDetailsDomain> {
        val movieDetailsPath = "${MoviePath.MoviePath.value}/$movieId"

        return tmdbClient.get(movieDetailsPath) {
            parameter(MoviePath.LanguageKey.value, language.code)
        }
            .runCatchingResult<MovieDetailsDTO, MovieDetailsDomain>(
                mapResponse = { it.convert() }
            )
    }
}
