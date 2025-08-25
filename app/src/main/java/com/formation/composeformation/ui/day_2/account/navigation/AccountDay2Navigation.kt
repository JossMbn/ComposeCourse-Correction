package com.formation.composeformation.ui.day_2.account.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_2.account.AccountDay2Root
import com.formation.composeformation.ui.day_2.utils.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
data object AccountDay2Route : TopLevelRoute {
    override val title: String
        get() = "Account"

    override val selectedIcon: ImageVector
        get() = Icons.Filled.AccountCircle

    override val unselectedIcon: ImageVector
        get() = Icons.Outlined.AccountCircle
}

data class AccountDay2Navigator(
    val navigateBack: () -> Unit = {}
)

fun NavController.createAccountDay2Navigator() = AccountDay2Navigator(
    navigateBack = {}
)

fun NavGraphBuilder.accountDay2Page(
    navigator: AccountDay2Navigator
) {
    composable<AccountDay2Route> {
        AccountDay2Root(navigator = navigator)
    }
}
