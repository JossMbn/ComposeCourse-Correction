package com.formation.composeformation.ui.day_2.utils

import androidx.compose.ui.graphics.vector.ImageVector
import com.formation.composeformation.ui.day_2.account.navigation.AccountDay2Route
import com.formation.composeformation.ui.day_2.home.navigation.HomeDay2Route
import com.formation.composeformation.ui.day_2.search.navigation.SearchDay2Route

interface TopLevelRoute {
    val title: String
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
}

val TOP_LEVEL_ROUTES : List<TopLevelRoute> = listOf(HomeDay2Route, SearchDay2Route, AccountDay2Route)
