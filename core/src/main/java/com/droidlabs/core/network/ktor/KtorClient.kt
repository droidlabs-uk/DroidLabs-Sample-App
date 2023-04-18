package com.droidlabs.core.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

object KtorClient {

    private const val AUTH_HEADER = "Authorization"
    private var API_KEY = "XYZ" //TODO

    private val client = HttpClient(Android) {
        defaultRequest {
            header(AUTH_HEADER, "BEARER $API_KEY")
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        install(ContentNegotiation) {
            json()
        }
    }

    val getInstance = client
}