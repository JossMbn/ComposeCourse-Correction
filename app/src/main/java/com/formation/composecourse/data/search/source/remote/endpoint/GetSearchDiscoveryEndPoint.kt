package com.formation.composecourse.data.search.source.remote.endpoint

import com.formation.composecourse.data.movie.source.remote.dto.MovieDataDTO
import com.formation.composecourse.data.movie.source.remote.dto.convert
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class GetSearchDiscoveryEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(): Result<MovieDataDomain> {
        return tmdbClient.get(SearchPath.DiscoveryPath.value)
            .runCatchingResult<MovieDataDTO, MovieDataDomain>(
                mapResponse = { it.convert() }
            )
    }
}
