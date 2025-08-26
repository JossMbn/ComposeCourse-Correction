package com.formation.composecourse.ui.search.model

import com.formation.composecourse.domain.movie.model.MovieDomain
import com.formation.composecourse.domain.search.model.SearchDomain

data class SearchState(
    val searchValue: String = "",
    val discovery: List<MovieDomain> = emptyList(),
    val cast: List<SearchDomain> = emptyList(),
    val movies: List<SearchDomain> = emptyList(),
    val tvShows: List<SearchDomain> = emptyList()
)
