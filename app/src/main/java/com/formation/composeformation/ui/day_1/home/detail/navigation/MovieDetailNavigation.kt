package com.formation.composeformation.ui.day_1.home.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_1.home.detail.presentation.DetailRoot
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val movieId: Int
)

fun NavHostController.navigateToDetail(movieId: Int) = navigate(DetailRoute(movieId))

data class DetailNavigator(
    val navigateBack: () -> Unit = {}
)

fun NavGraphBuilder.detailPage(
    navigator: DetailNavigator
) {
    composable<DetailRoute> {
        DetailRoot(navigator = navigator)
    }
}
