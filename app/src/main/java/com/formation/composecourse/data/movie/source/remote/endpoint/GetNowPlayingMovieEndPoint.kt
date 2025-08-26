package com.formation.composecourse.data.movie.source.remote.endpoint

import com.formation.composecourse.data.movie.source.remote.dto.MovieDataDTO
import com.formation.composecourse.data.movie.source.remote.dto.convert
import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetNowPlayingMovieEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        page: String = "1",
        language: LanguageCode = LanguageCode.EN_US
    ): Result<MovieDataDomain> {
        return tmdbClient.get(MoviePath.NowPlayingPath.value) {
            parameter(MoviePath.PageKey.value, page)
            parameter(MoviePath.LanguageKey.value, language.code)
        }
            .runCatchingResult<MovieDataDTO, MovieDataDomain>(
                mapResponse = { it.convert() }
            )
    }
}
