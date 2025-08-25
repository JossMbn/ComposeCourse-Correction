package com.formation.composeformation.ui.day_2.search.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_2.detail.navigation.navigateToMovieDetails
import com.formation.composeformation.ui.day_2.search.SearchDay2Root
import com.formation.composeformation.ui.day_2.utils.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
data object SearchDay2Route : TopLevelRoute {
    override val title: String
        get() = "Search"

    override val selectedIcon: ImageVector
        get() = Icons.Filled.Search

    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.Search
}

data class SearchDay2Navigator(
    val navigateBack: () -> Unit = {},
    val navigateToMovieDetails: (Int) -> Unit = {}
)

fun NavController.createSearchDay2Navigator() = SearchDay2Navigator(
    navigateBack = {},
    navigateToMovieDetails = this::navigateToMovieDetails
)

fun NavGraphBuilder.searchDay2Page(
    navigator: SearchDay2Navigator
) {
    composable<SearchDay2Route> {
        SearchDay2Root(navigator = navigator)
    }
}
