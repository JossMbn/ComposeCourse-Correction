package com.formation.composeformation.data.search.source.remote

import com.formation.composeformation.data.search.source.remote.endpoint.GetSearchByQueryEndPoint
import com.formation.composeformation.data.search.source.remote.endpoint.GetSearchDiscoveryEndPoint
import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.search.model.SearchDataDomain
import javax.inject.Inject

interface SearchRemoteDataSource {

    suspend fun getSearchByQuery(
        query: String,
        language: LanguageCode = LanguageCode.EN_US,
        page: Int = 1
    ): Result<SearchDataDomain>

    suspend fun getSearchDiscovery(): Result<MovieDataDomain>
}

class SearchRemoteDataSourceImpl @Inject constructor(
    private val getSearchByQueryEndPoint: GetSearchByQueryEndPoint,
    private val getSearchDiscoveryEndPoint: GetSearchDiscoveryEndPoint
) : SearchRemoteDataSource {

    override suspend fun getSearchByQuery(
        query: String,
        language: LanguageCode,
        page: Int
    ): Result<SearchDataDomain> {
        return getSearchByQueryEndPoint(
            query = query,
            language = language,
            page = page
        )
    }

    override suspend fun getSearchDiscovery(): Result<MovieDataDomain> {
        return getSearchDiscoveryEndPoint()
    }
}
