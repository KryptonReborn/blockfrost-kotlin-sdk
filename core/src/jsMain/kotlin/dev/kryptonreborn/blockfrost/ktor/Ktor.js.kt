package dev.kryptonreborn.blockfrost.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json

actual fun createHttpClient(projectId: String?): HttpClient {
    return HttpClient(Js) {
        install(ContentNegotiation) {
            json(Ktor.json)
        }
        install(Logging) {
            logger =
                object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d("Logger Ktor => $message")
                    }
                }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                co.touchlab.kermit.Logger.d {
                    "HTTP status: ${response.status.value}"
                }
            }
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header("project_id", projectId)
        }
    }
}
