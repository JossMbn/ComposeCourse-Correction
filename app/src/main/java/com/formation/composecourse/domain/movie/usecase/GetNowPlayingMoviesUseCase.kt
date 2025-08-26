package com.formation.composecourse.domain.movie.usecase

import com.formation.composecourse.domain.movie.model.MovieDataDomain
import com.formation.composecourse.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): Result<MovieDataDomain> {
        return movieRepository.getNowPlayingMovie()
    }
}
