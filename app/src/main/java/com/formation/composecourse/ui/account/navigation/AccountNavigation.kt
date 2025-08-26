package com.formation.composecourse.ui.account.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composecourse.ui.account.presentation.AccountPageRoot
import com.formation.composecourse.ui.home.utils.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
data object AccountRoute: TopLevelRoute {
    override val title: String
        get() = "Account"
    override val selectedIcon: ImageVector
        get() = Icons.Rounded.Person
    override val unselectedIcon: ImageVector
        get() = Icons.Rounded.PersonOutline
}

fun NavGraphBuilder.accountPage() {
    composable<AccountRoute> {
        AccountPageRoot()
    }
}
