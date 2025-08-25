package com.formation.composeformation.ui.day_2.onboarding.model

sealed interface OnBoardingAction {
    data object OnNextButtonClick : OnBoardingAction
    data object OnSkipButtonClick : OnBoardingAction
    data object OnStartButtonClick : OnBoardingAction
}
