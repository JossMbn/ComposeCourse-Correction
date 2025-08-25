package com.formation.composeformation.ui.day_2.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme
import com.formation.composeformation.ui.day_2.account.model.AccountDay2Action
import com.formation.composeformation.ui.day_2.account.model.AccountDay2State
import com.formation.composeformation.ui.day_2.account.navigation.AccountDay2Navigator

@Composable
fun AccountDay2Root(
    viewModel: AccountDay2ViewModel = hiltViewModel(),
    navigator: AccountDay2Navigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AccountDay2Page(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@Composable
fun AccountDay2Page(
    state: AccountDay2State,
    onAction: (AccountDay2Action) -> Unit,
    navigator: AccountDay2Navigator
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("AccountDay2Page", color = Color.White)
    }
}

@Preview
@Composable
private fun AccountDay2PagePreview() {
    ComposeFormationTheme {
        AccountDay2Page(
            state = AccountDay2State(),
            onAction = {},
            navigator = AccountDay2Navigator()
        )
    }
}
