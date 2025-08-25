package com.formation.composeformation.ui.day_2.detail.model

import com.formation.composeformation.domain.movie.model.MovieCreditsDomain
import com.formation.composeformation.domain.movie.model.MovieDetailsDomain

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
