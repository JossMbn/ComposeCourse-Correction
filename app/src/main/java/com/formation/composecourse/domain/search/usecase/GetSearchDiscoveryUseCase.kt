package com.formation.composecourse.domain.search.usecase

import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.domain.search.repository.SearchRepository
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
