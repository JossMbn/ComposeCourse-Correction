package com.formation.composeformation.data.search.source.remote.endpoint

import com.formation.composeformation.data.movie.source.remote.dto.MovieDataDTO
import com.formation.composeformation.data.movie.source.remote.dto.convert
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.network.utils.runCatchingResult
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
