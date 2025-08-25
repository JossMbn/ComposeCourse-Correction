package com.formation.composeformation.domain.search.usecase

import com.formation.composeformation.data.utils.LanguageCode
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.search.repository.SearchRepository
import javax.inject.Inject

class GetSearchDiscoveryUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(
        language: LanguageCode = LanguageCode.EN_US,
        page: Int = 1
    ): Result<MovieDataDomain> {
        return searchRepository.getSearchDiscovery()
            .mapCatching {
                it.copy(
                    results = it.results.filter { movie ->
                        movie.posterPath.isNotEmpty() &&
                            movie.title.isNotEmpty()
                    }
                )
            }
    }
}
