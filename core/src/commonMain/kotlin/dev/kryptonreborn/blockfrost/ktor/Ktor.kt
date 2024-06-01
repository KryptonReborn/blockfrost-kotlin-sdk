package dev.kryptonreborn.blockfrost.ktor

import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.base.handleResponseFromString
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.encodedPath
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

expect fun createHttpClient(blockfrostConfig: BlockfrostConfig): HttpClient

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
