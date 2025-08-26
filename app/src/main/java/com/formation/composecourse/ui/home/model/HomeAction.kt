package com.formation.composecourse.ui.home.model

enum class FilterSection {
    Movies,
    TvShows
}

sealed interface HomeAction {
    data class OnFilterSectionClick(val section: FilterSection) : HomeAction
}
