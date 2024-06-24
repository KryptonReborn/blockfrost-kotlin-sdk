package dev.kryptonreborn.blockfrost

import com.goncalossilva.resources.Resource
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
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray

object TestKtorClient {
    private fun httpClient(engine: HttpClientEngine) =
        HttpClient(engine) {
            install(ContentNegotiation) {
                json(Ktor.json)
            }
        }

    internal fun createMockHttpClient(
        path: String,
        responseContent: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ): HttpClient {
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
        return httpClient(mockEngine)
    }

    internal inline fun <reified T> String.resourceToExpectedData() = Ktor.json.decodeFromString<T>(Resource(this).readText())

    internal inline fun <reified T : Any> String.parseFirstElementInArray(): T {
        val resource = Resource(this).readText()
        val jsonElement = Ktor.json.parseToJsonElement(resource).jsonArray.first()
        return Ktor.json.decodeFromJsonElement(jsonElement)
    }
}
