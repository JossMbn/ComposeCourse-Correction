package com.formation.composeformation.data.movie.source.remote

import com.formation.composeformation.data.movie.source.remote.endpoint.GetMovieCreditEndPoint
import com.formation.composeformation.data.movie.source.remote.endpoint.GetMovieDetailsEndPoint
import com.formation.composeformation.data.movie.source.remote.endpoint.GetNowPlayingMovieEndPoint
import com.formation.composeformation.data.movie.source.remote.endpoint.GetPopularMovieEndPoint
import com.formation.composeformation.data.movie.source.remote.endpoint.GetTopRatedMovieEndPoint
import com.formation.composeformation.data.movie.source.remote.endpoint.GetUpcomingMovieEndPoint
import com.formation.composeformation.domain.movie.model.MovieCreditsDomain
import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.movie.model.MovieDetailsDomain
import javax.inject.Inject

interface MovieRemoteDataSource {

    suspend fun getNowPlayingMovie(): Result<MovieDataDomain>

    suspend fun getPopularMovie(): Result<MovieDataDomain>

    suspend fun getTopRatedMovie(): Result<MovieDataDomain>

    suspend fun getUpcomingMovie(): Result<MovieDataDomain>

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain>

    suspend fun getMovieCredits(movieId: Int): Result<MovieCreditsDomain>
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val getNowPlayingMovieEndPoint: GetNowPlayingMovieEndPoint,
    private val getPopularMovieEndPoint: GetPopularMovieEndPoint,
    private val getTopRatedMovieEndPoint: GetTopRatedMovieEndPoint,
    private val getUpcomingMovieEndPoint: GetUpcomingMovieEndPoint,
    private val getMovieDetailsEndPoint: GetMovieDetailsEndPoint,
    private val getMovieCreditEndPoint: GetMovieCreditEndPoint
) : MovieRemoteDataSource {

    override suspend fun getNowPlayingMovie(): Result<MovieDataDomain> {
        return getNowPlayingMovieEndPoint()
    }

    override suspend fun getPopularMovie(): Result<MovieDataDomain> {
        return getPopularMovieEndPoint()
    }

    override suspend fun getTopRatedMovie(): Result<MovieDataDomain> {
        return getTopRatedMovieEndPoint()
    }

    override suspend fun getUpcomingMovie(): Result<MovieDataDomain> {
        return getUpcomingMovieEndPoint()
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain> {
        return getMovieDetailsEndPoint(movieId = movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): Result<MovieCreditsDomain> {
        return getMovieCreditEndPoint(movieId = movieId)
    }
}
