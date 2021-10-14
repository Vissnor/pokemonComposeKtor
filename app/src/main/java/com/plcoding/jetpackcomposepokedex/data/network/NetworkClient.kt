package com.plcoding.jetpackcomposepokedex.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.*
import io.ktor.http.*
import timber.log.Timber

private const val TIME_OUT = 60_000
private const val BASE_URL = "pokeapi.co/api/v2"

fun createKtor(): HttpClient {
    return ktorHttpClient
}

private val ktorHttpClient = HttpClient(Android) {

    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.v(message)
            }

        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Timber.d("HTTP status: ${response.status.value}")
        }
    }

    install(DefaultRequest) {
        host = BASE_URL
        url {
            protocol = URLProtocol.HTTPS
        }
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}