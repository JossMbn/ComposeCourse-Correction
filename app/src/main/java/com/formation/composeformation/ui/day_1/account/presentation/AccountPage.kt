package com.formation.composeformation.ui.day_1.account.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composeformation.R
import com.formation.composeformation.designsystem.component.HeaderView
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme
import com.formation.composeformation.domain.common.model.AppTheme
import com.formation.composeformation.ui.day_1.account.component.AccountExpandableCardView
import com.formation.composeformation.ui.day_1.account.component.AccountItemView
import com.formation.composeformation.ui.day_1.account.component.AccountThemeItem
import com.formation.composeformation.ui.day_1.account.component.ProfileView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountPageRoot(viewModel: AccountViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AccountPage(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountPage(
    state: AccountState,
    onAction: (AccountAction) -> Unit
) {
    Scaffold(
        topBar = {
            HeaderView(
                title = stringResource(R.string.common_account),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        AccountPageContent(
            modifier = Modifier.padding(innerPadding),
            state = state,
            onAction = onAction
        )
    }
}

@Composable
private fun AccountPageContent(
    modifier: Modifier = Modifier,
    state: AccountState,
    onAction: (AccountAction) -> Unit
) {
    val scrollState = rememberScrollState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileView()

        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountExpandableCardView(
                modifier = Modifier.fillMaxWidth(),
                title = "Account"
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Information about your account",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "Test test",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            AccountItemView(
                modifier = Modifier.fillMaxWidth(),
                title = "Theme",
                icon = ImageVector.vectorResource(R.drawable.moon_theme),
                onClick = { showBottomSheet = true }
            )
        }
    }

    if (showBottomSheet) {
        AppThemeBottomSheet(
            theme = state.theme,
            onDismiss = {
                showBottomSheet = false
            },
            onThemeChange = { newTheme ->
                onAction(AccountAction.OnThemeChanged(newTheme))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppThemeBottomSheet(
    theme: AppTheme,
    onDismiss: () -> Unit,
    onThemeChange: (AppTheme) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose your theme",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AppTheme.entries.forEach { entry ->
                    AccountThemeItem(
                        selected = entry == theme,
                        label = entry.name,
                        onClick = { onThemeChange(entry) }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun AccountPagePreview() {
    var theme by remember { mutableStateOf(AppTheme.System) }

    ComposeFormationTheme(theme = theme) {
        AccountPage(
            state = AccountState(
                theme = theme
            ),
            onAction = { action ->
                when (action) {
                    is AccountAction.OnThemeChanged -> {
                        theme = action.theme
                    }
                }
            }
        )
    }
}
