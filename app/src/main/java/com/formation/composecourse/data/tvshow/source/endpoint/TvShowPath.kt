package com.formation.composecourse.data.tvshow.source.endpoint

enum class TvShowPath(val value: String) {
    // Search Endpoints Paths
    TvPath("tv"),
    AiringTodayPath("${TvPath.value}/airing_today"),
    OnTheAirPath("${TvPath.value}/on_the_air"),
    PopularPath("${TvPath.value}/popular"),
    TopRatedPath("${TvPath.value}/top_rated"),
    DetailsPath("${TvPath.value}/{series_id}"),

    // Params
    PageKey("page"),
    LanguageKey("language")
}
