package com.formation.composecourse.di

import com.formation.composecourse.data.common.source.endpoint.AppPreferencesDataSource
import com.formation.composecourse.data.common.source.endpoint.AppPreferencesDataSourceImpl
import com.formation.composecourse.data.movie.source.remote.MovieRemoteDataSource
import com.formation.composecourse.data.movie.source.remote.MovieRemoteDataSourceImpl
import com.formation.composecourse.data.search.source.remote.SearchRemoteDataSource
import com.formation.composecourse.data.search.source.remote.SearchRemoteDataSourceImpl
import com.formation.composecourse.data.tvshow.source.TvShowRemoteDataSource
import com.formation.composecourse.data.tvshow.source.TvShowRemoteDataSourceImpl
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
