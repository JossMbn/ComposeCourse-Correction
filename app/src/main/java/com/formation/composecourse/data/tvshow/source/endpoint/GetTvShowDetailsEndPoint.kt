package com.formation.composecourse.data.tvshow.source.endpoint

import com.formation.composecourse.data.tvshow.source.dto.TvShowDetailsDTO
import com.formation.composecourse.data.tvshow.source.dto.convert
import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.tvshow.model.TvShowDetailsDomain
import com.formation.composecourse.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetTvShowDetailsEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        tvShowId: Int,
        language: LanguageCode
    ): Result<TvShowDetailsDomain> {
        val url = "${TvShowPath.TvPath.value}/$tvShowId"

        return tmdbClient.get(url) {
            parameter(TvShowPath.LanguageKey.value, language.code)
        }
            .runCatchingResult<TvShowDetailsDTO, TvShowDetailsDomain>(
                mapResponse = { it.convert() }
            )
    }
}
