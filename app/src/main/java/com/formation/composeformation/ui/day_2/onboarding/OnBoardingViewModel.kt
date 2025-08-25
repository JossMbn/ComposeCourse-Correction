package com.formation.composeformation.ui.day_2.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composeformation.domain.common.usecase.UpdateOnBoardingCompletionUseCase
import com.formation.composeformation.ui.day_2.onboarding.model.OnBoardingAction
import com.formation.composeformation.ui.day_2.onboarding.model.OnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateOnBoardingCompletionUseCase: UpdateOnBoardingCompletionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OnBoardingState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = OnBoardingState()
        )

    fun onAction(action: OnBoardingAction) {
        when (action) {
            OnBoardingAction.OnNextButtonClick -> {
                updateState(currentPage = _state.value.currentPage + 1)
            }

            OnBoardingAction.OnSkipButtonClick,
            OnBoardingAction.OnStartButtonClick -> updateOnBoardingCompletion()
        }
    }

    private fun updateOnBoardingCompletion() {
        viewModelScope.launch {
            updateOnBoardingCompletionUseCase(isCompleted = true)
        }
    }

    private fun updateState(
        currentPage: Int = _state.value.currentPage
    ) {
        _state.update { currentState ->
            currentState.copy(
                currentPage = currentPage
            )
        }
    }
}
