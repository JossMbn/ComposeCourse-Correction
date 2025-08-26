package com.formation.composecourse.domain.search.repository

import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.domain.search.model.SearchDataDomain

interface SearchRepository {

    suspend fun getSearchByQuery(
        query: String,
        language: LanguageCode = LanguageCode.EN_US,
        page: Int = 1
    ): Result<SearchDataDomain>

    suspend fun getSearchDiscovery(): Result<MovieDataDomain>
}
