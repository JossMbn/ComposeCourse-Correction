package com.formation.composeformation.ui.day_2.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composeformation.domain.movie.usecase.GetMovieHomeSectionUseCase
import com.formation.composeformation.domain.movie.usecase.MovieHomeSection
import com.formation.composeformation.domain.tvshow.usecase.GetTvShowHomeSectionUseCase
import com.formation.composeformation.domain.tvshow.usecase.TvShowHomeSection
import com.formation.composeformation.ui.day_2.home.model.FilterSection
import com.formation.composeformation.ui.day_2.home.model.HomeDay2Action
import com.formation.composeformation.ui.day_2.home.model.HomeDay2ContentView
import com.formation.composeformation.ui.day_2.home.model.HomeDay2State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDay2ViewModel @Inject constructor(
    private val getMovieHomeSectionUseCase: GetMovieHomeSectionUseCase,
    private val getTvShowHomeSectionUseCase: GetTvShowHomeSectionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeDay2State())
    val state = _state
        .onStart {
            loadData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeDay2State()
        )

    fun onAction(action: HomeDay2Action) {
        when (action) {
            is HomeDay2Action.OnFilterSectionClick -> updateState(currentFilterSection = action.section)
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
        contentView: HomeDay2ContentView = state.value.contentView,
        currentFilterSection: FilterSection = state.value.currentFilterSection,
        movieSection: MovieHomeSection? = state.value.movieSection,
        tvShowSection: TvShowHomeSection? = state.value.tvShowSection,
    ) {
        _state.update { currentState ->
            currentState.copy(
                contentView = contentView,
                currentFilterSection = currentFilterSection,
                movieSection = movieSection,
                tvShowSection = tvShowSection
            )
        }
    }
}
