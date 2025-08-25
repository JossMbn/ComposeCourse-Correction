package com.formation.composeformation.data.search.repository

import com.formation.composeformation.data.search.source.remote.SearchRemoteDataSource
import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.search.model.SearchDataDomain
import com.formation.composeformation.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override suspend fun getSearchByQuery(
        query: String,
        language: LanguageCode,
        page: Int
    ): Result<SearchDataDomain> {
        return searchRemoteDataSource.getSearchByQuery(
            query = query,
            language = language,
            page = page
        )
    }

    override suspend fun getSearchDiscovery(): Result<MovieDataDomain> {
        return searchRemoteDataSource.getSearchDiscovery()
    }
}
