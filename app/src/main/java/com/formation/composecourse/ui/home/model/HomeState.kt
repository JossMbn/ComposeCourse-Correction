package com.formation.composecourse.ui.home.model

import com.formation.composecourse.domain.movie.usecase.MovieHomeSection
import com.formation.composecourse.domain.tvshow.usecase.TvShowHomeSection
import com.formation.composecourse.ui.home.component.PosterCarouselData

data class HomeState(
    val currentFilterSection: FilterSection = FilterSection.Movies,
    val contentList: List<PosterCarouselData> = emptyList(),
    val movieSection: MovieHomeSection? = null,
    val tvShowSection: TvShowHomeSection? = null
)
