package com.formation.composecourse.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composecourse.domain.common.usecase.UpdateOnBoardingCompletionUseCase
import com.formation.composecourse.ui.onboarding.model.OnBoardingAction
import com.formation.composecourse.ui.onboarding.model.OnBoardingState
import com.formation.composecourse.ui.onboarding.model.PosterData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateOnBoardingCompletionUseCase: UpdateOnBoardingCompletionUseCase
) : ViewModel() {

    private var posterPosition: Int = 0

    private val _state = MutableStateFlow(OnBoardingState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = OnBoardingState()
        )

    fun onAction(action: OnBoardingAction) {
        when (action) {
            OnBoardingAction.OnNextButtonClick -> {
                posterPosition += 1
                updateState(poster = PosterData.entries[posterPosition])
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
        poster: PosterData = _state.value.poster
    ) {
        _state.update { currentState ->
            currentState.copy(
                poster = poster
            )
        }
    }
}
