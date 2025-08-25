package com.formation.composeformation.ui.day_2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.formation.composeformation.ui.day_2.account.navigation.accountDay2Page
import com.formation.composeformation.ui.day_2.account.navigation.createAccountDay2Navigator
import com.formation.composeformation.ui.day_2.detail.navigation.createMovieDetailsNavigator
import com.formation.composeformation.ui.day_2.detail.navigation.movieDetailsPage
import com.formation.composeformation.ui.day_2.home.navigation.HomeDay2Route
import com.formation.composeformation.ui.day_2.home.navigation.createHomeDay2Navigator
import com.formation.composeformation.ui.day_2.home.navigation.homeDay2Page
import com.formation.composeformation.ui.day_2.search.navigation.createSearchDay2Navigator
import com.formation.composeformation.ui.day_2.search.navigation.searchDay2Page
import com.formation.composeformation.ui.day_2.utils.TOP_LEVEL_ROUTES

@Composable
fun ComposeCourseDay2App() {
    val controller = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
                    val isSelected = currentRoute == topLevelRoute::class.java.name

                    NavigationBarItem(
                        selected = isSelected,
                        icon = {
                            Icon(
                                imageVector = if (isSelected) topLevelRoute.selectedIcon else topLevelRoute.unselectedIcon,
                                contentDescription = topLevelRoute.title
                            )
                        },
                        onClick = {
                            controller.navigate(topLevelRoute) {
                                popUpTo(controller.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            navController = controller,
            startDestination = HomeDay2Route
        ) {
            homeDay2Page(navigator = controller.createHomeDay2Navigator())
            searchDay2Page(navigator = controller.createSearchDay2Navigator())
            accountDay2Page(navigator = controller.createAccountDay2Navigator())
            movieDetailsPage(navigator = controller.createMovieDetailsNavigator())
        }
    }
}
