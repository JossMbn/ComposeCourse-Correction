package com.formation.composeformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composeformation.domain.common.model.AppPreferences
import com.formation.composeformation.domain.common.usecase.ObserveAppPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ComposeCourseViewModel @Inject constructor(
    observeAppPreferencesUseCase: ObserveAppPreferencesUseCase
) : ViewModel() {

    val preferences: StateFlow<AppPreferences?> = observeAppPreferencesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )
}
