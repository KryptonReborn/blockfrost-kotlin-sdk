package dev.kryptonreborn.blockfrost.ktor

import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.base.handleResponseFromString
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
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
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

internal object Ktor {
    val json =
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            coerceInputValues = true
        }

    fun httpClient(blockfrostConfig: BlockfrostConfig) = createHttpClient(blockfrostConfig)
}

internal expect val httpEngine: HttpClientEngineFactory<HttpClientEngineConfig>

private fun createHttpClient(blockfrostConfig: BlockfrostConfig): HttpClient {
    return HttpClient(httpEngine) {
        defaultRequest {
            url(blockfrostConfig.networkType.url)
        }
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
            level = blockfrostConfig.logLevel.value
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
            header("project_id", blockfrostConfig.projectId)
        }
    }
}

@OptIn(InternalAPI::class)
internal suspend inline fun <reified T> HttpClient.fetchResource(
    path: String,
    method: HttpMethod = HttpMethod.Get,
    requestBody: Any? = null,
): T {
    val response: HttpResponse =
        request {
            url {
                encodedPath += path
            }
            this.method = method
            contentType(ContentType.Application.Json)
            requestBody?.let {
                body = requestBody
            }
        }

    val responseBody = response.bodyAsText()
    return handleResponseFromString<T>(responseBody)
}
