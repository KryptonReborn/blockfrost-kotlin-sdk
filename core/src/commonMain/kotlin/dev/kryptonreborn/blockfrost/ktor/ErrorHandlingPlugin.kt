package dev.kryptonreborn.blockfrost.ktor

import dev.kryptonreborn.blockfrost.base.ApiError
import dev.kryptonreborn.blockfrost.base.NetworkErrorException
import dev.kryptonreborn.blockfrost.base.ServerErrorException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.http.HttpStatusCode
import io.ktor.util.AttributeKey

object ErrorHandlingPlugin : HttpClientPlugin<Unit, ErrorHandlingPlugin> {
    override val key = AttributeKey<ErrorHandlingPlugin>("ErrorHandlingPlugin")

    override fun install(
        plugin: ErrorHandlingPlugin,
        scope: HttpClient,
    ) {
        scope.receivePipeline.intercept(HttpReceivePipeline.After) { response ->
            when (response.status) {
                HttpStatusCode.InternalServerError -> throw ServerErrorException(response.body())
                HttpStatusCode.ServiceUnavailable -> throw NetworkErrorException
                else -> {
                    if (response.status != HttpStatusCode.OK) {
                        throw response.body<ApiError>()
                    }
                }
            }
        }
    }

    override fun prepare(block: Unit.() -> Unit): ErrorHandlingPlugin {
        return this
    }
}
