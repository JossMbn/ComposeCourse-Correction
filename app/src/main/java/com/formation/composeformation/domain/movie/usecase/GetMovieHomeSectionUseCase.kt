package com.formation.composeformation.domain.movie.usecase

import com.formation.composeformation.domain.movie.model.MovieDomain
import com.formation.composeformation.ui.day_2.home.component.PosterCarouselData
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

data class MovieHomeSection(
    val nowPlaying: List<PosterCarouselData> = emptyList(),
    val popular: List<PosterCarouselData>,
    val topRated: List<PosterCarouselData>,
    val upcoming: List<PosterCarouselData> = emptyList()
)

class GetMovieHomeSectionUseCase @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) {

    suspend operator fun invoke(): MovieHomeSection = coroutineScope {
        val nowPlayingDeferred = async { getNowPlayingMoviesUseCase() }
        val popularDeferred = async { getPopularMoviesUseCase() }
        val topRatedDeferred = async { getTopRatedMovieUseCase() }
        val upcomingDeferred = async { getUpcomingMoviesUseCase() }

        val result = awaitAll(
            nowPlayingDeferred,
            popularDeferred,
            topRatedDeferred,
            upcomingDeferred
        )

        val nowPlayingResult: List<MovieDomain> = result[0].getOrNull()?.results ?: emptyList()
        val popularResult: List<MovieDomain> = result[1].getOrNull()?.results ?: emptyList()
        val topRatedResult: List<MovieDomain> = result[2].getOrNull()?.results ?: emptyList()
        val upcomingResult: List<MovieDomain> = result[3].getOrNull()?.results ?: emptyList()

        return@coroutineScope MovieHomeSection(
            nowPlaying = makePosterCarouselData(nowPlayingResult),
            popular = makePosterCarouselData(popularResult),
            topRated = makePosterCarouselData(topRatedResult),
            upcoming = makePosterCarouselData(upcomingResult)
        )
    }

    private fun makePosterCarouselData(items: List<MovieDomain>): List<PosterCarouselData> {
        return items
            .filter { it.posterPath.isNotEmpty() }
            .sortedByDescending { it.popularity }
            .map { movie ->
                PosterCarouselData(
                    id = movie.id,
                    posterPath = movie.posterPath
                )
            }
    }
}
