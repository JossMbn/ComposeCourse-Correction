package com.formation.composeformation.ui.day_1

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.formation.composeformation.ui.day_1.account.navigation.AccountPageRoute
import com.formation.composeformation.ui.day_1.account.navigation.accountPage
import com.formation.composeformation.ui.day_1.home.detail.navigation.DetailNavigator
import com.formation.composeformation.ui.day_1.home.detail.navigation.detailPage
import com.formation.composeformation.ui.day_1.home.detail.navigation.navigateToDetail
import com.formation.composeformation.ui.day_1.home.home.navigation.HomePageNavigator
import com.formation.composeformation.ui.day_1.home.home.navigation.HomePageRoute
import com.formation.composeformation.ui.day_1.home.home.navigation.homePage
import com.formation.composeformation.ui.day_1.search.navigation.SearchPageNavigator
import com.formation.composeformation.ui.day_1.search.navigation.SearchPageRoute
import com.formation.composeformation.ui.day_1.search.navigation.searchPage

enum class NavigationBarItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Any
) {
    Home(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = HomePageRoute
    ),
    Search(
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        route = SearchPageRoute
    ),
    Account(
        title = "Account",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        route = AccountPageRoute
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComposeCourseDay1App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isInTopLevelDestination = NavigationBarItems.entries.find {
        currentRoute == it.route::class.java.name
    } != null

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = isInTopLevelDestination,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    NavigationBarItems.entries.forEach { item ->
                        val isSelected = currentRoute == item.route::class.java.name

                        NavigationBarItem(
                            selected = isSelected,
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
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
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomePageRoute
        ) {
            homePage(
                navigator = HomePageNavigator(
                    navigateToDetail = navController::navigateToDetail
                )
            )
            detailPage(
                navigator = DetailNavigator(
                    navigateBack = navController::popBackStack
                )
            )
            searchPage(
                navigator = SearchPageNavigator(
                    navigateToDetail = navController::navigateToDetail
                )
            )
            accountPage()
        }
    }
}
