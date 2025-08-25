package com.formation.composeformation.ui.day_2.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme
import com.formation.composeformation.ui.day_2.onboarding.component.OnBoardingFooterView
import com.formation.composeformation.ui.day_2.onboarding.component.OnBoardingPosterView
import com.formation.composeformation.ui.day_2.onboarding.component.SkipButton
import com.formation.composeformation.ui.day_2.onboarding.model.OnBoardingAction
import com.formation.composeformation.ui.day_2.onboarding.model.OnBoardingState

@Composable
fun OnBoardingRoot(
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    OnBoardingPage(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun OnBoardingPage(
    state: OnBoardingState,
    onAction: (OnBoardingAction) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        OnBoardingPosterView(
            modifier = Modifier.fillMaxSize(),
            posterId = state.posterId
        )

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 24.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                SkipButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        onAction(OnBoardingAction.OnSkipButtonClick)
                        //navigator.navigateToHomePage()
                    }
                )

                OnBoardingFooterView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    title = state.title,
                    subtitle = state.subtitle,
                    buttonText = state.buttonText,
                    onClick = {
                        if (state.currentPage >= 2) {
                            onAction(OnBoardingAction.OnStartButtonClick)
                            //navigator.navigateToHomePage()
                        } else {
                            onAction(OnBoardingAction.OnNextButtonClick)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnBoardingPagePreview() {
    ComposeFormationTheme {
        OnBoardingPage(
            state = OnBoardingState(),
            onAction = {}
        )
    }
}
