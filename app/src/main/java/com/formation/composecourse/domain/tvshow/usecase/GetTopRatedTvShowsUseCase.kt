package com.formation.composecourse.domain.tvshow.usecase

import com.formation.composecourse.domain.tvshow.model.TvShowDataDomain
import com.formation.composecourse.domain.tvshow.repository.TvShowRepository
import javax.inject.Inject

class GetTopRatedTvShowsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {

    suspend operator fun invoke(): Result<TvShowDataDomain> {
        return tvShowRepository.getTopRatedTvShow()
    }
}
