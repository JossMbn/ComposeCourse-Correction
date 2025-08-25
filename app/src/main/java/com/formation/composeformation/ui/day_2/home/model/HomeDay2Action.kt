package com.formation.composeformation.ui.day_2.home.model

enum class FilterSection {
    Movies,
    TvShows
}

sealed interface HomeDay2Action {
    data class OnFilterSectionClick(val section: FilterSection) : HomeDay2Action
}
