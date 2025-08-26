package com.formation.composecourse.domain.tvshow.repository

import com.formation.composecourse.domain.tvshow.model.TvShowDataDomain
import com.formation.composecourse.domain.tvshow.model.TvShowDetailsDomain

interface TvShowRepository {

    suspend fun getAiringTodayTvShow(page: Int = 1): Result<TvShowDataDomain>

    suspend fun getOnTheAirTvShow(page: Int = 1): Result<TvShowDataDomain>

    suspend fun getPopularTvShow(page: Int = 1): Result<TvShowDataDomain>

    suspend fun getTopRatedTvShow(page: Int = 1): Result<TvShowDataDomain>

    suspend fun getTvShowDetails(tvShowId: Int): Result<TvShowDetailsDomain>
}
