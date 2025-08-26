package com.formation.composecourse.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
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
import com.formation.composecourse.ui.account.navigation.accountPage
import com.formation.composecourse.ui.detail.navigation.createMovieDetailsNavigator
import com.formation.composecourse.ui.detail.navigation.movieDetailsPage
import com.formation.composecourse.ui.home.navigation.HomeRoute
import com.formation.composecourse.ui.home.navigation.createHomeNavigator
import com.formation.composecourse.ui.home.navigation.homePage
import com.formation.composecourse.ui.home.utils.TOP_LEVEL_ROUTES
import com.formation.composecourse.ui.search.navigation.createSearchNavigator
import com.formation.composecourse.ui.search.navigation.searchPage

@Composable
fun ComposeCourseApp() {
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
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding), // Need to consume the insets to avoid them to be reapply in subScaffold of the NavHost.
            navController = controller,
            startDestination = HomeRoute
        ) {
            homePage(navigator = controller.createHomeNavigator())
            searchPage(navigator = controller.createSearchNavigator())
            accountPage()
            movieDetailsPage(navigator = controller.createMovieDetailsNavigator())
        }
    }
}
