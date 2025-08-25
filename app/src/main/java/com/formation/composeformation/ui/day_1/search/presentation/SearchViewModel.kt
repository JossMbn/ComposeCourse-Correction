package com.formation.composeformation.ui.day_1.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.formation.composeformation.domain.search.model.SearchDomain
import com.formation.composeformation.domain.search.usecase.GetSearchByQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchState(
    val query: String = "",
    val people: List<SearchDomain> = listOf(),
    val movies: List<SearchDomain> = listOf(),
    val tvShows: List<SearchDomain> = listOf()
)

sealed interface SearchAction {
    data class OnQueryChange(val query: String) : SearchAction
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchByQueryUseCase: GetSearchByQueryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val queryChanges = MutableSharedFlow<String>()

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnQueryChange -> updateQuery(query = action.query)
        }
    }

    init {
        observeQueryChanges()
    }

    private fun updateQuery(query: String) {
        viewModelScope.launch {
            updateState(query = query)
            queryChanges.emit(query)
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeQueryChanges() {
        viewModelScope.launch {
            queryChanges
                .debounce(800)
                .collect { query ->
                    searchByQuery(query)
                }
        }
    }

    private fun searchByQuery(query: String, page: Int = 1) {
        viewModelScope.launch {
            getSearchByQueryUseCase(
                query = query,
                page = page
            )
                .onSuccess { searchData ->
                    updateState(
                        people = searchData.cast,
                        movies = searchData.movies,
                        tvShows = searchData.tvShows
                    )
                }
                .onFailure {
                    Log.d("Search", "searchByQuery: $it")
                }
        }
    }

    private fun updateState(
        query: String = _state.value.query,
        people: List<SearchDomain> = _state.value.people,
        movies: List<SearchDomain> = _state.value.movies,
        tvShows: List<SearchDomain> = _state.value.tvShows
    ) {
        _state.update { currentState ->
            currentState.copy(
                query = query,
                people = people,
                movies = movies,
                tvShows = tvShows
            )
        }
    }
}
