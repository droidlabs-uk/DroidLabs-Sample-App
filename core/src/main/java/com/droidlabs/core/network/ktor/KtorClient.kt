package com.droidlabs.core.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TIMEOUT_15_SEC = 15000L
object KtorClient {

    private val client = HttpClient(Android) {
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_15_SEC
            connectTimeoutMillis = TIMEOUT_15_SEC
            socketTimeoutMillis = TIMEOUT_15_SEC
        }

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    val getInstance = client
}
