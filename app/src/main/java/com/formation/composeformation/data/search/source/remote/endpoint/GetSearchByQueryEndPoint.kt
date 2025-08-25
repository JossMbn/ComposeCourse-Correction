package com.formation.composeformation.data.search.source.remote.endpoint

import com.formation.composeformation.data.search.source.remote.dto.SearchDataDTO
import com.formation.composeformation.data.search.source.remote.dto.convert
import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.search.model.SearchDataDomain
import com.formation.composeformation.network.utils.runCatchingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GetSearchByQueryEndPoint @Inject constructor(
    private val tmdbClient: HttpClient
) {
    suspend operator fun invoke(
        query: String,
        language: LanguageCode = LanguageCode.EN_US,
        page: Int
    ): Result<SearchDataDomain> {
        return tmdbClient.get(SearchPath.MultiSearchPath.value) {
            parameter(SearchPath.QueryKey.value, query)
            parameter(SearchPath.PageKey.value, page.toString())
            parameter(SearchPath.LanguageKey.value, language.code)
        }
            .runCatchingResult<SearchDataDTO, SearchDataDomain>(
                mapResponse = { it.convert() }
            )
    }
}
