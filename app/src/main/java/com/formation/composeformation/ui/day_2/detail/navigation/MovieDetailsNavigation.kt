package com.formation.composeformation.ui.day_2.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_2.detail.MovieDetailsRoot
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsRoute(
    val movieId: Int
)

data class MovieDetailsNavigator(
    val navigateBack: () -> Unit = {}
)

fun NavController.navigateToMovieDetails(movieId: Int) = navigate(
    MovieDetailsRoute(movieId = movieId)
)

fun NavController.createMovieDetailsNavigator() = MovieDetailsNavigator(
    navigateBack = ::navigateUp
)

fun NavGraphBuilder.movieDetailsPage(
    navigator: MovieDetailsNavigator
) {
    composable<MovieDetailsRoute> {
        MovieDetailsRoot(navigator = navigator)
    }
}
