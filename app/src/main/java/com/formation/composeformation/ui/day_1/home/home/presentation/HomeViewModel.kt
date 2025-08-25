package com.formation.composeformation.ui.day_1.home.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composeformation.domain.movie.model.MovieDomain
import com.formation.composeformation.domain.movie.usecase.GetPopularMoviesUseCase
import com.formation.composeformation.domain.movie.usecase.GetTopRatedMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val isLoading: Boolean = false,
    val popularMovies: List<MovieDomain> = emptyList(),
    val topRatedMovies: List<MovieDomain> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = HomeState()
        )

    private fun loadData() {
        loadPopularMovie()
        loadTopRatedMovie()
    }

    private fun loadPopularMovie() {
        viewModelScope.launch {
            getPopularMoviesUseCase()
                .onSuccess {
                    updateState(popularMovies = it.results)
                }
                .onFailure { error ->
                    Log.d(
                        "ComposeFormationError",
                        "GetPopularMoviesUseCase error: ${error.message}"
                    )
                }
        }
    }

    private fun loadTopRatedMovie() {
        viewModelScope.launch {
            getTopRatedMovieUseCase()
                .onSuccess {
                    updateState(topRatedMovies = it.results)
                }
                .onFailure { error ->
                    Log.d(
                        "ComposeFormationError",
                        "GetPopularMoviesUseCase error: ${error.message}"
                    )
                }
        }
    }

    private fun updateState(
        popularMovies: List<MovieDomain> = _state.value.popularMovies,
        topRatedMovies: List<MovieDomain> = _state.value.topRatedMovies
    ) {
        _state.update { currentState ->
            currentState.copy(
                popularMovies = popularMovies,
                topRatedMovies = topRatedMovies
            )
        }
    }
}
