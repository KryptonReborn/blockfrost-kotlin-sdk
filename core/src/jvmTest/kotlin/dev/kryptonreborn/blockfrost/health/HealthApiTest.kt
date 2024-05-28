package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.KtorClientTest
import dev.kryptonreborn.blockfrost.base.ApiError
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_API_ROOT
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH_CLOCK
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertFailsWith

class HealthApiTest {
    @Test
    fun testApiRootReturnCorrectData() =
        runTest {
            val mockEngine =
                MockEngine { request ->
                    when (request.url.encodedPath) {
                        "/api/v0$PATH_API_ROOT" ->
                            respond(
                                content =
                                    "{\n" +
                                        "  \"url\": \"https://blockfrost.io/\",\n" +
                                        "  \"version\": \"0.1.0\"\n" +
                                        "}",
                                status = HttpStatusCode.OK,
                                headers =
                                    headersOf(
                                        HttpHeaders.ContentType,
                                        ContentType.Application.Json.toString(),
                                    ),
                            )

                        else -> error("Unhandled ${request.url.encodedPath}")
                    }
                }

            val httpClient = KtorClientTest.httpClient(mockEngine)
            val healthApi = HealthApi(httpClient)
            val result = healthApi.getApiRoot()
            assertEquals("", "https://blockfrost.io/", result.url)
            assertEquals("", "0.1.0", result.version)
        }

    @Test
    fun testApiRootReturn400() =
        runTest {
            val mockEngine =
                MockEngine { request ->
                    when (request.url.encodedPath) {
                        "/api/v0$PATH_API_ROOT" ->
                            respond(
                                content =
                                    "{\n" +
                                        "  \"status_code\": 400,\n" +
                                        "  \"error\": \"Bad Request\",\n" +
                                        "  \"message\": \"Backend did not understand your request.\"\n" +
                                        "}",
                                status = HttpStatusCode.RequestTimeout,
                                headers =
                                    headersOf(
                                        HttpHeaders.ContentType,
                                        ContentType.Application.Json.toString(),
                                    ),
                            )

                        else -> error("Unhandled ${request.url.encodedPath}")
                    }
                }

            val httpClient = KtorClientTest.httpClient(mockEngine)
            val healthApi = HealthApi(httpClient)
            val exception =
                assertFailsWith<ApiError> {
                    healthApi.getApiRoot()
                }
            assertEquals("", "Backend did not understand your request.", exception.message)
        }

    @Test
    fun testHealthReturnCorrectData() =
        runTest {
            val mockEngine =
                MockEngine { request ->
                    when (request.url.encodedPath) {
                        "/api/v0$PATH_HEALTH" ->
                            respond(
                                content =
                                    "{\n" +
                                        "  \"is_healthy\": true\n" +
                                        "}",
                                status = HttpStatusCode.OK,
                                headers =
                                    headersOf(
                                        HttpHeaders.ContentType,
                                        ContentType.Application.Json.toString(),
                                    ),
                            )

                        else -> error("Unhandled ${request.url.encodedPath}")
                    }
                }

            val httpClient = KtorClientTest.httpClient(mockEngine)
            val healthApi = HealthApi(httpClient)
            val result = healthApi.getHealth()
            assertEquals("", true, result.isHealthy)
        }

    @Test
    fun testHealthReturn400() =
        runTest {
            val mockEngine =
                MockEngine { request ->
                    when (request.url.encodedPath) {
                        "/api/v0$PATH_HEALTH" ->
                            respond(
                                content =
                                    "{\n" +
                                        "  \"status_code\": 400,\n" +
                                        "  \"error\": \"Bad Request\",\n" +
                                        "  \"message\": \"Backend did not understand your request.\"\n" +
                                        "}",
                                status = HttpStatusCode.RequestTimeout,
                                headers =
                                    headersOf(
                                        HttpHeaders.ContentType,
                                        ContentType.Application.Json.toString(),
                                    ),
                            )

                        else -> error("Unhandled ${request.url.encodedPath}")
                    }
                }

            val httpClient = KtorClientTest.httpClient(mockEngine)
            val healthApi = HealthApi(httpClient)
            val exception =
                assertFailsWith<ApiError> {
                    healthApi.getHealth()
                }
            assertEquals("", "Backend did not understand your request.", exception.message)
        }

    @Test
    fun testHealthClockReturnCorrectData() =
        runTest {
            val mockEngine =
                MockEngine { request ->
                    when (request.url.encodedPath) {
                        "/api/v0$PATH_HEALTH_CLOCK" ->
                            respond(
                                content =
                                    "{\n" +
                                        "  \"server_time\": 1620000000\n" +
                                        "}",
                                status = HttpStatusCode.OK,
                                headers =
                                    headersOf(
                                        HttpHeaders.ContentType,
                                        ContentType.Application.Json.toString(),
                                    ),
                            )

                        else -> error("Unhandled ${request.url.encodedPath}")
                    }
                }

            val httpClient = KtorClientTest.httpClient(mockEngine)
            val healthApi = HealthApi(httpClient)
            val result = healthApi.getCurrentBackendTime()
            assertEquals("", 1620000000L, result.serverTime)
        }

    @Test
    fun testHealthClockReturn400() =
        runTest {
            val mockEngine =
                MockEngine { request ->
                    when (request.url.encodedPath) {
                        "/api/v0$PATH_HEALTH_CLOCK" ->
                            respond(
                                content =
                                    "{\n" +
                                        "  \"status_code\": 400,\n" +
                                        "  \"error\": \"Bad Request\",\n" +
                                        "  \"message\": \"Backend did not understand your request.\"\n" +
                                        "}",
                                status = HttpStatusCode.RequestTimeout,
                                headers =
                                    headersOf(
                                        HttpHeaders.ContentType,
                                        ContentType.Application.Json.toString(),
                                    ),
                            )

                        else -> error("Unhandled ${request.url.encodedPath}")
                    }
                }

            val httpClient = KtorClientTest.httpClient(mockEngine)
            val healthApi = HealthApi(httpClient)
            val exception =
                assertFailsWith<ApiError> {
                    healthApi.getCurrentBackendTime()
                }
            assertEquals("", "Backend did not understand your request.", exception.message)
        }
}
