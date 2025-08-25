package com.formation.composeformation.data.movie.source.remote.endpoint

import com.formation.composeformation.data.movie.source.remote.dto.MovieDataDTO
import com.formation.composeformation.data.movie.source.remote.dto.convert
import com.formation.composeformation.data.search.source.remote.dto.convert
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetPopularMovieEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(): Result<MovieDataDomain> {
        return tmdbClient.get(MoviePath.PopularMoviePath.value) {
            parameter(MoviePath.PageKey.value, "1")
        }
            .runCatchingResult<MovieDataDTO, MovieDataDomain>(
                mapResponse = { it.convert() }
            )
    }
}
