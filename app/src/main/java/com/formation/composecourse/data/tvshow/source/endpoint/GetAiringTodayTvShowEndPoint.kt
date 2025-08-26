package com.formation.composecourse.data.tvshow.source.endpoint

import com.formation.composecourse.data.tvshow.source.dto.TvShowDataDTO
import com.formation.composecourse.data.tvshow.source.dto.convert
import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.tvshow.model.TvShowDataDomain
import com.formation.composecourse.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetAiringTodayTvShowEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        page: Int,
        language: LanguageCode
    ): Result<TvShowDataDomain> {
        return tmdbClient.get(TvShowPath.AiringTodayPath.value) {
            parameter(TvShowPath.PageKey.value, page)
            parameter(TvShowPath.LanguageKey.value, language.code)
        }
            .runCatchingResult<TvShowDataDTO, TvShowDataDomain>(
                mapResponse = { it.convert() }
            )
    }
}
