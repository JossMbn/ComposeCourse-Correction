package com.formation.composeformation.data.tvshow.source.endpoint

import com.formation.composeformation.data.tvshow.source.dto.TvShowDataDTO
import com.formation.composeformation.data.tvshow.source.dto.convert
import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.tvshow.model.TvShowDataDomain
import com.formation.composeformation.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetTopRatedTvShowEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        page: Int,
        language: LanguageCode
    ): Result<TvShowDataDomain> {
        return tmdbClient.get(TvShowPath.TopRatedPath.value) {
            parameter(TvShowPath.PageKey.value, page)
            parameter(TvShowPath.LanguageKey.value, language.code)
        }
            .runCatchingResult<TvShowDataDTO, TvShowDataDomain>(
                mapResponse = { it.convert() }
            )
    }
}
