package com.formation.composeformation.data.movie.source.remote.endpoint

import com.formation.composeformation.data.movie.source.remote.dto.MovieCreditsDTO
import com.formation.composeformation.data.movie.source.remote.dto.convert
import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.movie.model.MovieCreditsDomain
import com.formation.composeformation.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetMovieCreditEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        movieId: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<MovieCreditsDomain> {
        val movieCreditPath = "${MoviePath.MoviePath.value}/$movieId/${MoviePath.CreditsPath.value}"

        return tmdbClient.get(movieCreditPath) {
            parameter(MoviePath.LanguageKey.value, language.code)
        }
            .runCatchingResult<MovieCreditsDTO, MovieCreditsDomain>(
                mapResponse = { it.convert() }
            )
    }
}
