package dev.kryptonreborn.blockfrost.ktor

import dev.kryptonreborn.blockfrost.BlockfrostConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json

actual fun createHttpClient(blockfrostConfig: BlockfrostConfig): HttpClient {
    return HttpClient(CIO) {
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
            url(blockfrostConfig.networkType.url)
        }
    }
}
