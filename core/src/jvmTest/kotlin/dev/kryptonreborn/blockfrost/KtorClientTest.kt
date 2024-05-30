package dev.kryptonreborn.blockfrost

import dev.kryptonreborn.blockfrost.ktor.ErrorHandlingPlugin
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientTest {
    fun httpClient(engine: HttpClientEngine) =
        HttpClient(engine) {
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
            install(ErrorHandlingPlugin)
        }

    fun createMockEngine(
        path: String,
        responseContent: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ): MockEngine {
        return MockEngine { request ->
            when (request.url.encodedPath) {
                path ->
                    respond(
                        content = responseContent,
                        status = status,
                        headers =
                            headersOf(
                                HttpHeaders.ContentType,
                                ContentType.Application.Json.toString(),
                            ),
                    )

                else -> error("Unhandled ${request.url.encodedPath}")
            }
        }
    }
}
