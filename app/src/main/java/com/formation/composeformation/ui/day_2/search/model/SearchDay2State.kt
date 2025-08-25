package com.formation.composeformation.ui.day_2.search.model

import com.formation.composeformation.domain.movie.model.MovieDomain
import com.formation.composeformation.domain.search.model.SearchDomain

data class SearchDay2State(
    val searchValue: String = "",
    val discovery: List<MovieDomain> = emptyList(),
    val cast: List<SearchDomain> = emptyList(),
    val movies: List<SearchDomain> = emptyList(),
    val tvShows: List<SearchDomain> = emptyList()
)
