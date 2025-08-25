package com.formation.composeformation.ui.day_2.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_2.home.HomeDay2Root
import com.formation.composeformation.ui.day_2.utils.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeDay2Route : TopLevelRoute {
    override val title: String
        get() = "Home"

    override val selectedIcon: ImageVector
        get() = Icons.Filled.Home

    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Home
}

data class HomeDay2Navigator(
    val navigateBack: () -> Unit = {}
)

fun NavController.createHomeDay2Navigator() = HomeDay2Navigator(
    navigateBack = {}
)

fun NavGraphBuilder.homeDay2Page(
    navigator: HomeDay2Navigator
) {
    composable<HomeDay2Route> {
        HomeDay2Root(navigator = navigator)
    }
}
