package com.formation.composecourse.ui.search.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composecourse.ui.detail.navigation.navigateToMovieDetails
import com.formation.composecourse.ui.home.utils.TopLevelRoute
import com.formation.composecourse.ui.search.SearchRoot
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute : TopLevelRoute {
    override val title: String
        get() = "Search"

    override val selectedIcon: ImageVector
        get() = Icons.Filled.Search

    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Search
}

data class SearchNavigator(
    val navigateBack: () -> Unit = {},
    val navigateToMovieDetails: (Int) -> Unit = {}
)

fun NavController.createSearchNavigator() = SearchNavigator(
    navigateBack = {},
    navigateToMovieDetails = this::navigateToMovieDetails
)

fun NavGraphBuilder.searchPage(
    navigator: SearchNavigator
) {
    composable<SearchRoute> {
        SearchRoot(navigator = navigator)
    }
}
