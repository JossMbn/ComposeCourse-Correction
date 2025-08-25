package com.formation.composeformation.di

import com.formation.composeformation.data.common.repository.AppPreferencesRepositoryImpl
import com.formation.composeformation.data.movie.repository.MovieRepositoryImpl
import com.formation.composeformation.data.search.repository.SearchRepositoryImpl
import com.formation.composeformation.data.tvshow.respository.TvShowRepositoryImpl
import com.formation.composeformation.domain.common.repository.AppPreferencesRepository
import com.formation.composeformation.domain.movie.repository.MovieRepository
import com.formation.composeformation.domain.search.repository.SearchRepository
import com.formation.composeformation.domain.tvshow.repository.TvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppPreferencesRepository(
        appPreferencesRepositoryImpl: AppPreferencesRepositoryImpl
    ): AppPreferencesRepository

    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    abstract fun bindTvShowRepository(
        tvShowRepositoryImpl: TvShowRepositoryImpl
    ): TvShowRepository
}
