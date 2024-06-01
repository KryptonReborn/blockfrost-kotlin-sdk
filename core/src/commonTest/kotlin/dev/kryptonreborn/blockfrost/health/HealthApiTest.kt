package dev.kryptonreborn.blockfrost.health

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient.createHealthApi
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_API_ROOT
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH_CLOCK
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class HealthApiTest {
    @Test
    fun testApiRootReturnCorrectData() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/api_root_200.json").readText()
            val healthApi = createHealthApi("/api/v0$PATH_API_ROOT", responseContent)
            val result = healthApi.getApiRoot()
            assertEquals("https://blockfrost.io/", result.url)
            assertEquals("0.1.0", result.version)
        }

    @Test
    fun testApiRootReturn200WithFailData() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/test_data_errors_response.json").readText()
            val healthApi =
                createHealthApi("/api/v0$PATH_API_ROOT", responseContent, HttpStatusCode.OK)

            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getApiRoot()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiRootReturn400() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/test_data_errors_response.json").readText()
            val healthApi =
                createHealthApi("/api/v0$PATH_API_ROOT", responseContent, HttpStatusCode.BadRequest)
            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getApiRoot()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testHealthReturnCorrectData() =
        runTest {
            val content = Resource("src/commonTest/resources/api_health_200.json").readText()
            val healthApi = createHealthApi("/api/v0$PATH_HEALTH", content)
            val result = healthApi.getHealth()
            assertEquals(true, result.isHealthy)
        }

    @Test
    fun testHealthReturn200WithFailData() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/test_data_errors_response.json").readText()
            val healthApi =
                createHealthApi("/api/v0$PATH_HEALTH", responseContent, HttpStatusCode.OK)
            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getHealth()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testHealthReturn400() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/test_data_errors_response.json").readText()
            val healthApi =
                createHealthApi("/api/v0$PATH_HEALTH", responseContent, HttpStatusCode.BadRequest)
            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getHealth()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testHealthClockReturnCorrectData() =
        runTest {
            val content = Resource("src/commonTest/resources/api_health_clock_200.json").readText()
            val healthApi = createHealthApi("/api/v0$PATH_HEALTH_CLOCK", content)
            val result = healthApi.getCurrentBackendTime()
            assertEquals(1620000000L, result.serverTime)
        }

    @Test
    fun testHealthClockReturn200WithFailData() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/test_data_errors_response.json").readText()
            val healthApi =
                createHealthApi("/api/v0$PATH_HEALTH_CLOCK", responseContent, HttpStatusCode.OK)
            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getCurrentBackendTime()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testHealthClockReturn400() =
        runTest {
            val responseContent =
                Resource("src/commonTest/resources/test_data_errors_response.json").readText()
            val healthApi =
                createHealthApi(
                    "/api/v0$PATH_HEALTH_CLOCK",
                    responseContent,
                    HttpStatusCode.BadRequest,
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getCurrentBackendTime()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }
}
