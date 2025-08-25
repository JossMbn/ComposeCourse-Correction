package com.formation.composeformation.ui.day_2.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composeformation.ui.day_2.account.model.AccountDay2Action
import com.formation.composeformation.ui.day_2.account.model.AccountDay2Event
import com.formation.composeformation.ui.day_2.account.model.AccountDay2State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountDay2ViewModel @Inject constructor() : ViewModel() {

    private val _event = MutableSharedFlow<AccountDay2Event>()
    val event = _event.asSharedFlow()

    private val _state = MutableStateFlow(AccountDay2State())
    val state = _state
        .onStart {
            // Load initial data here
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AccountDay2State()
        )

    fun onAction(action: AccountDay2Action) {
        when (action) {
            else -> {
                // Handle actions
            }
        }
    }

    private fun loadData() {
        // ...
    }
}
