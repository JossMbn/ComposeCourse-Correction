package com.formation.composecourse.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composecourse.domain.movie.usecase.GetMovieHomeSectionUseCase
import com.formation.composecourse.domain.movie.usecase.MovieHomeSection
import com.formation.composecourse.domain.tvshow.usecase.GetTvShowHomeSectionUseCase
import com.formation.composecourse.domain.tvshow.usecase.TvShowHomeSection
import com.formation.composecourse.ui.home.model.FilterSection
import com.formation.composecourse.ui.home.model.HomeAction
import com.formation.composecourse.ui.home.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieHomeSectionUseCase: GetMovieHomeSectionUseCase,
    private val getTvShowHomeSectionUseCase: GetTvShowHomeSectionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart {
            loadData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = HomeState()
        )

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnFilterSectionClick -> updateState(currentFilterSection = action.section)
        }
    }

    private fun loadData() {
        getMovieSections()
        getTvShowSections()
    }

    private fun getMovieSections() {
        viewModelScope.launch {
            updateState(movieSection = getMovieHomeSectionUseCase())
        }
    }

    private fun getTvShowSections() {
        viewModelScope.launch {
            updateState(tvShowSection = getTvShowHomeSectionUseCase())
        }
    }

    private fun updateState(
        currentFilterSection: FilterSection = state.value.currentFilterSection,
        movieSection: MovieHomeSection? = state.value.movieSection,
        tvShowSection: TvShowHomeSection? = state.value.tvShowSection,
    ) {
        _state.update { currentState ->
            currentState.copy(
                currentFilterSection = currentFilterSection,
                movieSection = movieSection,
                tvShowSection = tvShowSection
            )
        }
    }
}
