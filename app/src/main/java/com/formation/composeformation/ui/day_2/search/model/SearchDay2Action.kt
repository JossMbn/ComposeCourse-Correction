package com.formation.composeformation.ui.day_2.search.model

sealed interface SearchDay2Action {
    data class OnQueryChange(val query: String) : SearchDay2Action
}
