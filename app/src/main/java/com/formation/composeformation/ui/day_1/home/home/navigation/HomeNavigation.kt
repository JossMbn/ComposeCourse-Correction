package com.formation.composeformation.ui.day_1.home.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_1.home.home.presentation.HomePageRoot
import kotlinx.serialization.Serializable

@Serializable
data object HomePageRoute

data class HomePageNavigator(
    val navigateToDetail: (Int) -> Unit = {}
)

fun NavGraphBuilder.homePage(
    navigator: HomePageNavigator
) {
    composable<HomePageRoute> {
        HomePageRoot(navigator = navigator)
    }
}
