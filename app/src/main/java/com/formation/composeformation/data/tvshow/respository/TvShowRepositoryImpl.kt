package com.formation.composeformation.data.tvshow.respository

import com.formation.composeformation.data.tvshow.source.TvShowRemoteDataSource
import com.formation.composeformation.domain.tvshow.model.TvShowDataDomain
import com.formation.composeformation.domain.tvshow.model.TvShowDetailsDomain
import com.formation.composeformation.domain.tvshow.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {

    override suspend fun getAiringTodayTvShow(page: Int): Result<TvShowDataDomain> {
        return tvShowRemoteDataSource.getAiringTodayTvShow(page = page)
    }

    override suspend fun getOnTheAirTvShow(page: Int): Result<TvShowDataDomain> {
        return tvShowRemoteDataSource.getOnTheAirTvShow(page = page)
    }

    override suspend fun getPopularTvShow(page: Int): Result<TvShowDataDomain> {
        return tvShowRemoteDataSource.getPopularTvShow(page = page)
    }

    override suspend fun getTopRatedTvShow(page: Int): Result<TvShowDataDomain> {
        return tvShowRemoteDataSource.getTopRatedTvShow(page = page)
    }

    override suspend fun getTvShowDetails(tvShowId: Int): Result<TvShowDetailsDomain> {
        return tvShowRemoteDataSource.getTvShowDetails(tvShowId = tvShowId)
    }
}
