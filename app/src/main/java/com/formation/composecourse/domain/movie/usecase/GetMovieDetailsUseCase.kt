package com.formation.composecourse.domain.movie.usecase

import com.formation.composecourse.domain.movie.model.MovieCreditsDomain
import com.formation.composecourse.domain.movie.model.MovieDetailsDomain
import com.formation.composecourse.domain.movie.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

data class MovieDetailData(
    val details: MovieDetailsDomain?,
    val credits: MovieCreditsDomain?
)

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: Int): MovieDetailData = coroutineScope {
        val detailsDeferred = async { movieRepository.getMovieDetails(movieId) }
        val creditsDeferred = async { movieRepository.getMovieCredits(movieId) }

        val details = detailsDeferred.await().getOrNull()
        val credits = creditsDeferred.await().getOrNull()

        return@coroutineScope MovieDetailData(
            details = details,
            credits = credits
        )
    }
}
