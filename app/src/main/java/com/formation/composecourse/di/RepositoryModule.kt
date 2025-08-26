package com.formation.composecourse.di

import com.formation.composecourse.data.common.repository.AppPreferencesRepositoryImpl
import com.formation.composecourse.data.movie.repository.MovieRepositoryImpl
import com.formation.composecourse.data.search.repository.SearchRepositoryImpl
import com.formation.composecourse.data.tvshow.respository.TvShowRepositoryImpl
import com.formation.composecourse.domain.common.repository.AppPreferencesRepository
import com.formation.composecourse.domain.movie.repository.MovieRepository
import com.formation.composecourse.domain.search.repository.SearchRepository
import com.formation.composecourse.domain.tvshow.repository.TvShowRepository
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
