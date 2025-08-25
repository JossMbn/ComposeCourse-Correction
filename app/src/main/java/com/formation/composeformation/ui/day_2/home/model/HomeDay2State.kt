package com.formation.composeformation.ui.day_2.home.model

import com.formation.composeformation.domain.movie.usecase.MovieHomeSection
import com.formation.composeformation.domain.tvshow.usecase.TvShowHomeSection

enum class HomeDay2ContentView {
    Loading, Content
}

data class HomeDay2State(
    val contentView: HomeDay2ContentView = HomeDay2ContentView.Loading,
    val currentFilterSection: FilterSection = FilterSection.Movies,
    val movieSection: MovieHomeSection? = null,
    val tvShowSection: TvShowHomeSection? = null
)
