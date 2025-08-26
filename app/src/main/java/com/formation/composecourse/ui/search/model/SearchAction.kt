package com.formation.composecourse.ui.search.model

sealed interface SearchAction {
    data class OnQueryChange(val query: String) : SearchAction
}
