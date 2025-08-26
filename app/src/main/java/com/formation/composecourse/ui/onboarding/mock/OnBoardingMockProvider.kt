package com.formation.composecourse.ui.onboarding.mock

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.formation.composecourse.ui.onboarding.model.OnBoardingState
import com.formation.composecourse.ui.onboarding.model.PosterData

class OnBoardingMockProvider : PreviewParameterProvider<OnBoardingState> {

    private val firstStep = OnBoardingState(poster = PosterData.IronMan)

    private val secondStep = OnBoardingState(poster = PosterData.Batman)

    private val thirdStep = OnBoardingState(poster = PosterData.Dune)

    override val values: Sequence<OnBoardingState>
        get() = sequenceOf(
            firstStep,
            secondStep,
            thirdStep
        )
}
