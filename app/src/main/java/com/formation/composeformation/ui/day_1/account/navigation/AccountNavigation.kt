package com.formation.composeformation.ui.day_1.account.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.formation.composeformation.ui.day_1.account.presentation.AccountPageRoot
import kotlinx.serialization.Serializable

@Serializable
data object AccountPageRoute

fun NavGraphBuilder.accountPage() {
    composable<AccountPageRoute> {
        AccountPageRoot()
    }
}
