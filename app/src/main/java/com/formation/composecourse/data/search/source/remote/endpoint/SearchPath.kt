package com.formation.composecourse.data.search.source.remote.endpoint

enum class SearchPath(val value: String) {
    // Search Endpoints Paths
    SearchPath("search"),
    MultiSearchPath("${SearchPath.value}/multi"),
    DiscoveryPath("discover/movie"),


    // Params
    QueryKey("query"),
    PageKey("page"),
    LanguageKey("language")
}
