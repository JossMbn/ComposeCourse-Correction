package com.formation.composecourse.data.movie.source.remote.endpoint

import com.formation.composecourse.data.movie.source.remote.dto.MovieDataDTO
import com.formation.composecourse.data.movie.source.remote.dto.convert
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.network.utils.runCatchingResult
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
