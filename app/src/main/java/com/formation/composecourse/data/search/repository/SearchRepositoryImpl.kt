package com.formation.composecourse.data.search.repository

import com.formation.composecourse.data.search.source.remote.SearchRemoteDataSource
import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.domain.search.model.SearchDataDomain
import com.formation.composecourse.domain.search.repository.SearchRepository
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
