package com.formation.composecourse.ui.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composecourse.domain.common.model.AppTheme
import com.formation.composecourse.domain.common.usecase.ObserveAppPreferencesUseCase
import com.formation.composecourse.domain.common.usecase.UpdateAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AccountState(
    val theme: AppTheme = AppTheme.System
)

sealed interface AccountAction {
    data class OnThemeChanged(val theme: AppTheme) : AccountAction
}

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val observeAppPreferencesUseCase: ObserveAppPreferencesUseCase,
    private val updateAppThemeUseCase: UpdateAppThemeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AccountState())
    val state = _state.asStateFlow()

    init {
        observeAppPreferences()
    }

    fun onAction(action: AccountAction) {
        when (action) {
            is AccountAction.OnThemeChanged -> updateAppTheme(action.theme)
        }
    }

    private fun observeAppPreferences() {
        viewModelScope.launch {
            observeAppPreferencesUseCase().collect { preferences ->
                updateState(theme = preferences.theme)
            }
        }
    }

    private fun updateAppTheme(theme: AppTheme) {
        viewModelScope.launch {
            updateAppThemeUseCase(theme)
        }
    }

    private fun updateState(
        theme: AppTheme = _state.value.theme
    ) {
        _state.update { currentState ->
            currentState.copy(
                theme = theme
            )
        }
    }
}
