package dev.kryptonreborn.blockfrost.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.winhttp.WinHttp

actual val httpEngine: HttpClientEngineFactory<HttpClientEngineConfig> = WinHttp
