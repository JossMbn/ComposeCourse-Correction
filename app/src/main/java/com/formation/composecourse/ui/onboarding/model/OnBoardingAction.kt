package com.formation.composecourse.ui.onboarding.model

sealed interface OnBoardingAction {
    data object OnNextButtonClick : OnBoardingAction
    data object OnSkipButtonClick : OnBoardingAction
    data object OnStartButtonClick : OnBoardingAction
}
