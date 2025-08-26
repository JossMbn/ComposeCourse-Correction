package com.formation.composecourse.data.tvshow.source

import com.formation.composecourse.data.tvshow.source.endpoint.GetAiringTodayTvShowEndPoint
import com.formation.composecourse.data.tvshow.source.endpoint.GetOnTheAirTvShowEndPoint
import com.formation.composecourse.data.tvshow.source.endpoint.GetPopularTvShowEndPoint
import com.formation.composecourse.data.tvshow.source.endpoint.GetTopRatedTvShowEndPoint
import com.formation.composecourse.data.tvshow.source.endpoint.GetTvShowDetailsEndPoint
import com.formation.composecourse.data.utils.LanguageCode
import com.formation.composecourse.domain.tvshow.model.TvShowDataDomain
import com.formation.composecourse.domain.tvshow.model.TvShowDetailsDomain
import javax.inject.Inject

interface TvShowRemoteDataSource {
    suspend fun getAiringTodayTvShow(
        page: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<TvShowDataDomain>

    suspend fun getOnTheAirTvShow(
        page: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<TvShowDataDomain>

    suspend fun getPopularTvShow(
        page: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<TvShowDataDomain>

    suspend fun getTopRatedTvShow(
        page: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<TvShowDataDomain>

    suspend fun getTvShowDetails(
        tvShowId: Int,
        language: LanguageCode = LanguageCode.EN_US
    ): Result<TvShowDetailsDomain>
}

class TvShowRemoteDataSourceImpl @Inject constructor(
    private val getAiringTodayTvShowEndPoint: GetAiringTodayTvShowEndPoint,
    private val getOnTheAirTvShowEndPoint: GetOnTheAirTvShowEndPoint,
    private val getPopularTvShowEndPoint: GetPopularTvShowEndPoint,
    private val getTopRatedTvShowEndPoint: GetTopRatedTvShowEndPoint,
    private val getTvShowDetailsEndPoint: GetTvShowDetailsEndPoint
) : TvShowRemoteDataSource {
    override suspend fun getAiringTodayTvShow(
        page: Int,
        language: LanguageCode
    ): Result<TvShowDataDomain> {
        return getAiringTodayTvShowEndPoint(page = page, language = language)
    }

    override suspend fun getOnTheAirTvShow(
        page: Int,
        language: LanguageCode
    ): Result<TvShowDataDomain> {
        return getOnTheAirTvShowEndPoint(page = page, language = language)
    }

    override suspend fun getPopularTvShow(
        page: Int,
        language: LanguageCode
    ): Result<TvShowDataDomain> {
        return getPopularTvShowEndPoint(page = page, language = language)
    }

    override suspend fun getTopRatedTvShow(
        page: Int,
        language: LanguageCode
    ): Result<TvShowDataDomain> {
        return getTopRatedTvShowEndPoint(page = page, language = language)
    }

    override suspend fun getTvShowDetails(
        tvShowId: Int,
        language: LanguageCode
    ): Result<TvShowDetailsDomain> {
        return getTvShowDetailsEndPoint(tvShowId = tvShowId, language = language)
    }
}
