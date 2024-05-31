package dev.kryptonreborn.blockfrost.ktor

import BlockFrostKotlinSdk
import dev.kryptonreborn.blockfrost.base.handleResponseFromString
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger as KLogger

object Ktor {
    val httpClient =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                        coerceInputValues = true
                    },
                )
            }
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            KLogger.d("Logger Ktor => $message")
                        }
                    }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    KLogger.d {
                        "HTTP status: ${response.status.value}"
                    }
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("project_id", BlockFrostKotlinSdk.blockfrostConfig.projectId)
            }
        }
}

@OptIn(InternalAPI::class)
suspend inline fun <reified T> HttpClient.fetchResource(
    url: String,
    method: HttpMethod = HttpMethod.Get,
    requestBody: Any? = null,
): T {
    val response: HttpResponse =
        request(url) {
            this.method = method
            if (requestBody != null) {
                contentType(ContentType.Application.Json)
                body = requestBody
            }
        }

    val responseBody = response.bodyAsText()
    return handleResponseFromString<T>(responseBody)
}
