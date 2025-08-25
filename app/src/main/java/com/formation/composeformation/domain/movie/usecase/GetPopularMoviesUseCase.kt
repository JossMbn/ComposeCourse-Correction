package com.formation.composeformation.domain.movie.usecase

import com.formation.composeformation.domain.movie.model.MovieDataDomain
import com.formation.composeformation.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): Result<MovieDataDomain> {
        return movieRepository.getPopularMovie()
    }
}
