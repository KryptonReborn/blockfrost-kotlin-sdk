package dev.kryptonreborn.blockfrost.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual val httpEngine: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
