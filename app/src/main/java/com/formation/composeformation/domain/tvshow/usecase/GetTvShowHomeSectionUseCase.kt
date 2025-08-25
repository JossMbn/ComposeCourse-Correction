package com.formation.composeformation.domain.tvshow.usecase

import com.formation.composeformation.domain.tvshow.model.TvShowDetailsDomain
import com.formation.composeformation.domain.tvshow.model.TvShowDomain
import com.formation.composeformation.ui.day_2.home.component.PosterCarouselData
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

data class TvShowHomeSection(
    val headlineTvShow: TvShowDetailsDomain?,
    val airingToday: List<PosterCarouselData>,
    val onTheAir: List<PosterCarouselData>,
    val popular: List<PosterCarouselData>,
    val topRated: List<PosterCarouselData>
)

class GetTvShowHomeSectionUseCase @Inject constructor(
    private val getAiringTodayTvShowUseCase: GetAiringTodayTvShowsUseCase,
    private val getOnTheAirTvShowUseCase: GetOnTheAirTvShowsUseCase,
    private val getPopularTvShowUseCase: GetPopularTvShowsUseCase,
    private val getTopRatedTvShowUseCase: GetTopRatedTvShowsUseCase,
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase
) {

    suspend operator fun invoke(): TvShowHomeSection = coroutineScope {
        val airingTodayDeferred = async { getAiringTodayTvShowUseCase() }
        val onTheAirDeferred = async { getOnTheAirTvShowUseCase() }
        val popularDeferred = async { getPopularTvShowUseCase() }
        val topRatedDeferred = async { getTopRatedTvShowUseCase() }

        val results = awaitAll(
            airingTodayDeferred,
            onTheAirDeferred,
            popularDeferred,
            topRatedDeferred
        )

        val airingTodayResult: List<TvShowDomain> = results[0].getOrNull()?.results ?: emptyList()
        val onTheAirResult: List<TvShowDomain> = results[1].getOrNull()?.results ?: emptyList()
        val popularResult: List<TvShowDomain> = results[2].getOrNull()?.results ?: emptyList()
        val topRatedResult: List<TvShowDomain> = results[3].getOrNull()?.results ?: emptyList()

        val headlineTvShowId = topRatedResult.random().id
        val headlineTvShowDetailsDeferred = async {
            getTvShowDetailsUseCase(tvShowId = headlineTvShowId)
        }
        val headlineTvShowDetailsResult = headlineTvShowDetailsDeferred.await().getOrNull()

        return@coroutineScope TvShowHomeSection(
            headlineTvShow = headlineTvShowDetailsResult,
            airingToday = makePosterCarouselData(airingTodayResult),
            onTheAir = makePosterCarouselData(onTheAirResult),
            popular = makePosterCarouselData(popularResult),
            topRated = makePosterCarouselData(topRatedResult, headlineTvShowId)
        )
    }

    private fun makePosterCarouselData(
        items: List<TvShowDomain>,
        dropItemId: Int? = null
    ): List<PosterCarouselData> {
        return items
            .filter { it.posterPath.isNotEmpty() }
            .sortedByDescending { it.popularity }
            .let {
                if (dropItemId != null) {
                    it
                        .toMutableList()
                        .filter { it.id != dropItemId }
                        .toList()
                } else {
                    it
                }
            }
            .map { tvShow ->
                PosterCarouselData(
                    id = tvShow.id,
                    posterPath = tvShow.posterPath
                )
            }
    }
}
