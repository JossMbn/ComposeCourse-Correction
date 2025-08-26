package com.formation.composecourse.data.movie.source.remote.endpoint

enum class MoviePath(val value: String) {
    // Movie Endpoints Paths
    MoviePath("movie"),
    NowPlayingPath("${MoviePath.value}/now_playing"),
    PopularMoviePath("${MoviePath.value}/popular"),
    TopRatedMoviePath("${MoviePath.value}/top_rated"),
    UpcomingMoviePath("${MoviePath.value}/upcoming"),
    CreditsPath("credits"),

    // Params
    PageKey("page"),
    LanguageKey("language"),
    MovieIdKey("movie_id")

}
