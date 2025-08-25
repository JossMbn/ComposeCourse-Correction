package com.formation.composeformation.domain.tvshow.usecase

import com.formation.composeformation.domain.tvshow.model.TvShowDetailsDomain
import com.formation.composeformation.domain.tvshow.repository.TvShowRepository
import javax.inject.Inject

class GetTvShowDetailsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {

    suspend operator fun invoke(tvShowId: Int): Result<TvShowDetailsDomain> {
        return tvShowRepository.getTvShowDetails(tvShowId = tvShowId)
    }
}
