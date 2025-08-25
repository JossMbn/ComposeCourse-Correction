package com.formation.composeformation.di

import com.formation.composeformation.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val HEADER_ACCEPT_VALUE = "application/json"
    private const val HEADER_BEARER_VALUE = "Bearer ${BuildConfig.TMDP_API_KEY}"

    @Singleton
    @Provides
    fun provideTMDBHttpClient(): HttpClient = HttpClient(engine = OkHttp.create()) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }

        defaultRequest {
            header(
                key = HttpHeaders.Accept,
                value = HEADER_ACCEPT_VALUE
            )
            header(
                key = HttpHeaders.Authorization,
                value = HEADER_BEARER_VALUE
            )
            url(urlString = BuildConfig.TMDP_BASE_URL)
        }
    }
}
