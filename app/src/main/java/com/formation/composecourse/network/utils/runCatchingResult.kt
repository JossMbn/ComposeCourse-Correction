package com.formation.composecourse.network.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

suspend inline fun <reified InputType, OutputType> HttpResponse.runCatchingResult(
    crossinline mapResponse: (InputType) -> OutputType,
): Result<OutputType> {
    val response = try {
        this
    } catch (_: UnresolvedAddressException) {
        return Result.failure(NetworkError.NoInternet)
    } catch (_: SerializationException) {
        return Result.failure(NetworkError.Serialization)
    }

    return when (response.status.value) {
        in 200..299 -> {
            val result = response.body<InputType>()
            Result.success(mapResponse(result))
        }

        401 -> Result.failure(NetworkError.Unauthorized)
        409 -> Result.failure(NetworkError.Conflict)
        408 -> Result.failure(NetworkError.RequestTimeout)
        413 -> Result.failure(NetworkError.PayloadTooLarge)
        429 -> Result.failure(NetworkError.TooManyRequests)
        in 500..599 -> Result.failure(NetworkError.ServerError)

        else -> Result.failure(NetworkError.Unknown)
    }
}
