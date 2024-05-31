package dev.kryptonreborn.blockfrost

import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor
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

object TestKtorClient {
    private fun httpClient(engine: HttpClientEngine) =
        HttpClient(engine) {
            install(ContentNegotiation) {
                json(Ktor.json)
            }
        }

    internal fun createHealthApi(
        path: String,
        responseContent: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ): HealthApi {
        val blockfrostConfig =
            BlockfrostConfig(
                projectId = "your project id",
                logLevel = BlockfrostLogLevel.ALL,
            )

        val mockEngine =
            MockEngine { request ->
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
        val httpClient = httpClient(mockEngine)
        return HealthApi(httpClient, blockfrostConfig)
    }
}
