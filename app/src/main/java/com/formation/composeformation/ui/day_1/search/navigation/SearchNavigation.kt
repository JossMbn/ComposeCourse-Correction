package com.formation.composeformation.ui.day_1.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_1.search.presentation.SearchPageRoot
import kotlinx.serialization.Serializable

@Serializable
data object SearchPageRoute

data class SearchPageNavigator(
    val navigateToDetail: (Int) -> Unit = {}
)

fun NavGraphBuilder.searchPage(
    navigator: SearchPageNavigator
) {
    composable<SearchPageRoute> {
        SearchPageRoot(
            navigator = navigator
        )
    }
}
