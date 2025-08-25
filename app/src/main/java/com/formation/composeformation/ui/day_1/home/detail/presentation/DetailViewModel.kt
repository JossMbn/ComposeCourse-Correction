package com.formation.composeformation.ui.day_1.home.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.formation.composeformation.domain.movie.usecase.GetMovieDetailsUseCase
import com.formation.composeformation.ui.day_1.home.detail.model.DetailState
import com.formation.composeformation.ui.day_1.home.detail.navigation.DetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val navArgs = savedStateHandle.toRoute<DetailRoute>()

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailState()
        )

    private fun loadData() {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            val data = getMovieDetailsUseCase(movieId = navArgs.movieId)

            updateState(
                title = data.details?.title ?: "",
                overview = data.details?.overview ?: "",
                posterPath = data.details?.posterPath ?: ""
            )
        }
    }

    private fun updateState(
        title: String = _state.value.title,
        overview: String = _state.value.overview,
        posterPath: String = _state.value.posterPath
    ) {
        _state.update { currentState ->
            currentState.copy(
                title = title,
                overview = overview,
                posterPath = posterPath
            )
        }
    }
}
