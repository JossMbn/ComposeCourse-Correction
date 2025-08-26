package com.formation.composecourse.network.utils


sealed class NetworkError: Exception() {
    data object Unauthorized: NetworkError()
    data object Conflict: NetworkError()
    data object RequestTimeout: NetworkError()
    data object PayloadTooLarge: NetworkError()
    data object TooManyRequests: NetworkError()
    data object ServerError: NetworkError()
    data object NoInternet: NetworkError()
    data object Serialization: NetworkError()
    data object Unknown: NetworkError()
}
