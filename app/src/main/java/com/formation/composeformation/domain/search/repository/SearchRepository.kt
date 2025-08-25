package com.formation.composeformation.domain.search.repository

import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.movie.model.MovieDetailsDomain
import com.formation.composeformation.domain.search.model.SearchDataDomain

interface SearchRepository {

    suspend fun getSearchByQuery(
        query: String,
        language: LanguageCode = LanguageCode.EN_US,
        page: Int = 1
    ): Result<SearchDataDomain>

    suspend fun getSearchDiscovery(): Result<MovieDataDomain>
}
