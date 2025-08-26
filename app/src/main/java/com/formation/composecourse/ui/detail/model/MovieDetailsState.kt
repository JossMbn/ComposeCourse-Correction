package com.formation.composecourse.ui.detail.model

import com.formation.composecourse.domain.movie.model.MovieCreditsDomain
import com.formation.composecourse.domain.movie.model.MovieDetailsDomain

enum class MovieDetailsContentView {
    Loading, Content
}

data class MovieDetailsState(
    val contentView: MovieDetailsContentView = MovieDetailsContentView.Loading,
    val movieDetails: MovieDetailsDomain? = null,
    val credits: MovieCreditsDomain? = null
) {
    val movieYear: String? = movieDetails?.releaseDate?.year?.toString()
}
