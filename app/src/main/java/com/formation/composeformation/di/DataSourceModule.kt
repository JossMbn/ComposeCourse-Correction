package com.formation.composeformation.di

import com.formation.composeformation.data.common.source.endpoint.AppPreferencesDataSource
import com.formation.composeformation.data.common.source.endpoint.AppPreferencesDataSourceImpl
import com.formation.composeformation.data.movie.source.remote.MovieRemoteDataSource
import com.formation.composeformation.data.movie.source.remote.MovieRemoteDataSourceImpl
import com.formation.composeformation.data.search.source.remote.SearchRemoteDataSource
import com.formation.composeformation.data.search.source.remote.SearchRemoteDataSourceImpl
import com.formation.composeformation.data.tvshow.source.TvShowRemoteDataSource
import com.formation.composeformation.data.tvshow.source.TvShowRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAppPreferencesDataSource(
        appPreferencesDataSourceImpl: AppPreferencesDataSourceImpl
    ): AppPreferencesDataSource

    @Binds
    abstract fun bindMovieRemoteDataSource(
        movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource

    @Binds
    abstract fun bindSearchRemoteDataSource(
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl
    ): SearchRemoteDataSource

    @Binds
    abstract fun bindTvShowRemoteDataSource(
        tvShowRemoteDataSourceImpl: TvShowRemoteDataSourceImpl
    ): TvShowRemoteDataSource
}
