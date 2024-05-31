package dev.kryptonreborn.blockfrost.ktor

import dev.kryptonreborn.blockfrost.base.handleResponseFromString
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
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

    fun httpClient(projectId: String?) = createHttpClient(projectId)
}

expect fun createHttpClient(projectId: String?): HttpClient

@OptIn(InternalAPI::class)
internal suspend inline fun <reified T> HttpClient.fetchResource(
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
