package com.formation.composecourse.domain.movie.repository

import com.formation.composecourse.domain.movie.model.MovieCreditsDomain
import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.domain.movie.model.MovieDetailsDomain

interface MovieRepository {

    suspend fun getNowPlayingMovie(): Result<MovieDataDomain>

    suspend fun getPopularMovie(): Result<MovieDataDomain>

    suspend fun getTopRatedMovie(): Result<MovieDataDomain>

    suspend fun getUpcomingMovie(): Result<MovieDataDomain>

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain>

    suspend fun getMovieCredits(movieId: Int): Result<MovieCreditsDomain>
}
