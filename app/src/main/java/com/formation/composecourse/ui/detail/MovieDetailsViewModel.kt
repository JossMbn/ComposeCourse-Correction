package com.formation.composecourse.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.formation.composecourse.domain.movie.model.MovieCreditsDomain
import com.formation.composecourse.domain.movie.model.MovieDetailsDomain
import com.formation.composecourse.domain.movie.usecase.GetMovieDetailsUseCase
import com.formation.composecourse.ui.detail.model.MovieDetailsContentView
import com.formation.composecourse.ui.detail.model.MovieDetailsState
import com.formation.composecourse.ui.detail.navigation.MovieDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val navArgs = savedStateHandle.toRoute<MovieDetailsRoute>()

    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = MovieDetailsState()
        )

    private fun loadData() {
        viewModelScope.launch {
            updateState(contentView = MovieDetailsContentView.Loading)
            val data = getMovieDetailsUseCase.invoke(movieId = navArgs.movieId)

            updateState(
                movieDetails = data.details,
                credits = data.credits
            )
            updateState(contentView = MovieDetailsContentView.Content)
        }
    }

    private fun updateState(
        contentView: MovieDetailsContentView = _state.value.contentView,
        movieDetails: MovieDetailsDomain? = _state.value.movieDetails,
        credits: MovieCreditsDomain? = _state.value.credits
    ) {
        _state.update { currentState ->
            currentState.copy(
                contentView = contentView,
                movieDetails = movieDetails,
                credits = credits
            )
        }
    }
}
