package com.formation.composecourse.ui.onboarding

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composecourse.R
import com.formation.composecourse.designsystem.theme.ComposeCourseTheme
import com.formation.composecourse.ui.onboarding.component.OnBoardingFooterView
import com.formation.composecourse.ui.onboarding.component.OnBoardingPosterView
import com.formation.composecourse.ui.onboarding.component.SkipButton
import com.formation.composecourse.ui.onboarding.mock.OnBoardingMockProvider
import com.formation.composecourse.ui.onboarding.model.OnBoardingAction
import com.formation.composecourse.ui.onboarding.model.OnBoardingState
import com.formation.composecourse.ui.onboarding.model.PosterData

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
    val buttonText = when (state.poster) {
        PosterData.IronMan, PosterData.Batman -> stringResource(R.string.common_next)
        PosterData.Dune -> stringResource(R.string.common_start)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box {
            OnBoardingPosterView(
                modifier = Modifier.fillMaxSize(),
                posterId = state.poster.posterId
            )

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
                    }
                )

                OnBoardingFooterView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    title = stringResource(state.poster.title),
                    subtitle = stringResource(state.poster.subtitle),
                    buttonText = buttonText,
                    onClick = {
                        when (state.poster) {
                            PosterData.Dune -> onAction(OnBoardingAction.OnStartButtonClick)
                            else -> onAction(OnBoardingAction.OnNextButtonClick)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnBoardingPagePreview(
    @PreviewParameter(OnBoardingMockProvider::class) state: OnBoardingState
) {
    ComposeCourseTheme {
        OnBoardingPage(
            state = state,
            onAction = {}
        )
    }
}
