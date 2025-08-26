package com.formation.composecourse.ui.home.utils

import androidx.compose.ui.graphics.vector.ImageVector
import com.formation.composecourse.ui.account.navigation.AccountRoute
import com.formation.composecourse.ui.home.navigation.HomeRoute
import com.formation.composecourse.ui.search.navigation.SearchRoute

interface TopLevelRoute {
    val title: String
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
}

val TOP_LEVEL_ROUTES : List<TopLevelRoute> = listOf(HomeRoute, SearchRoute, AccountRoute)
