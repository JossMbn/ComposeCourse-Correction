package com.formation.composecourse.ui.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composecourse.ui.detail.navigation.navigateToMovieDetails
import com.formation.composecourse.ui.home.HomeRoot
import com.formation.composecourse.ui.home.utils.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : TopLevelRoute {
    override val title: String
        get() = "Home"

    override val selectedIcon: ImageVector
        get() = Icons.Filled.Home

    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Home
}

data class HomeNavigator(
    val navigateToMovieDetails: (Int) -> Unit = {}
)

fun NavController.createHomeNavigator() = HomeNavigator(
    navigateToMovieDetails = { this.navigateToMovieDetails(movieId = it) }
)

fun NavGraphBuilder.homePage(
    navigator: HomeNavigator
) {
    composable<HomeRoute> {
        HomeRoot(navigator = navigator)
    }
}
