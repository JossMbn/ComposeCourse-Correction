package com.formation.composecourse.data.movie.repository

import com.formation.composecourse.data.movie.source.remote.MovieRemoteDataSource
import com.formation.composecourse.domain.movie.model.MovieCreditsDomain
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.domain.movie.model.MovieDetailsDomain
import com.formation.composecourse.domain.movie.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getNowPlayingMovie(): Result<MovieDataDomain> {
        return movieRemoteDataSource.getNowPlayingMovie()
    }

    override suspend fun getPopularMovie(): Result<MovieDataDomain> {
        return movieRemoteDataSource.getPopularMovie()
    }

    override suspend fun getTopRatedMovie(): Result<MovieDataDomain> {
        return movieRemoteDataSource.getTopRatedMovie()
    }

    override suspend fun getUpcomingMovie(): Result<MovieDataDomain> {
        return movieRemoteDataSource.getUpcomingMovie()
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain> {
        return movieRemoteDataSource.getMovieDetails(movieId = movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): Result<MovieCreditsDomain> {
        return movieRemoteDataSource.getMovieCredits(movieId = movieId)
    }
}
