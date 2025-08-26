package com.formation.composecourse.domain.tvshow.usecase

import com.formation.composecourse.domain.tvshow.model.TvShowDetailsDomain
import com.formation.composecourse.domain.tvshow.repository.TvShowRepository
import javax.inject.Inject

class GetTvShowDetailsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {

    suspend operator fun invoke(tvShowId: Int): Result<TvShowDetailsDomain> {
        return tvShowRepository.getTvShowDetails(tvShowId = tvShowId)
    }
}
