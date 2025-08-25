package com.formation.composeformation.domain.tvshow.usecase

import com.formation.composeformation.domain.tvshow.model.TvShowDataDomain
import com.formation.composeformation.domain.tvshow.repository.TvShowRepository
import javax.inject.Inject

class GetPopularTvShowsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {

    suspend operator fun invoke(): Result<TvShowDataDomain> {
        return tvShowRepository.getPopularTvShow()
    }
}
