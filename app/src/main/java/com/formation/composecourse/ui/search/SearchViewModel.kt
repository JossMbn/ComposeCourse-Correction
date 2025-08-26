package com.formation.composecourse.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composecourse.domain.movie.model.MovieDomain
import com.formation.composecourse.domain.search.model.SearchDomain
import com.formation.composecourse.domain.search.usecase.GetSearchByQueryUseCase
import com.formation.composecourse.domain.search.usecase.GetSearchDiscoveryUseCase
import com.formation.composecourse.ui.search.model.SearchAction
import com.formation.composecourse.ui.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchByQueryUseCase: GetSearchByQueryUseCase,
    private val getSearchDiscoveryUseCase: GetSearchDiscoveryUseCase
) : ViewModel() {

    private val _searchValue = MutableStateFlow("")

    private val _state = MutableStateFlow(SearchState())
    val state = _state
        .onStart { loadDiscoveryData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SearchState()
        )

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnQueryChange -> {
                _searchValue.value = action.query
                if (action.query.isNotEmpty()) {
                    updateState(searchValue = action.query)
                } else {
                    updateState(
                        searchValue = action.query,
                        cast = emptyList(),
                        movies = emptyList(),
                        tvShows = emptyList()
                    )
                }
            }
        }
    }

    init {
        setupSearchValueObservation()
    }

    private fun setupSearchValueObservation() {
        viewModelScope.launch {
            _searchValue
                .debounce(500)
                .collectLatest {
                    getSearchByQuery(it)
                }
        }
    }

    private fun loadDiscoveryData() {
        viewModelScope.launch {
            getSearchDiscoveryUseCase()
                .onSuccess { movieData ->
                    updateState(discovery = movieData.results)
                }
        }
    }

    private fun getSearchByQuery(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) return@launch

            getSearchByQueryUseCase(query = query)
                .onSuccess { searchData ->
                    updateState(
                        cast = searchData.cast,
                        movies = searchData.movies,
                        tvShows = searchData.tvShows
                    )
                }
        }
    }

    private fun updateState(
        searchValue: String = _state.value.searchValue,
        discovery: List<MovieDomain> = _state.value.discovery,
        cast: List<SearchDomain> = _state.value.cast,
        movies: List<SearchDomain> = _state.value.movies,
        tvShows: List<SearchDomain> = _state.value.tvShows
    ) {
        _state.update { currentState ->
            currentState.copy(
                searchValue = searchValue,
                discovery = discovery,
                cast = cast,
                movies = movies,
                tvShows = tvShows
            )
        }
    }
}
