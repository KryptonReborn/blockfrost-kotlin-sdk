package dev.kryptonreborn.blockfrost.health

import BlockFrostKotlinSdk
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.BlockfrostLogLevel
import dev.kryptonreborn.blockfrost.KtorClientTest.createHealthApi
import dev.kryptonreborn.blockfrost.KtorClientTest.createHealthApiWithFailData
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
    init {
        BlockFrostKotlinSdk.initConfig(
            BlockfrostConfig(
                projectId = "your project id",
                logLevel = BlockfrostLogLevel.ALL,
            ),
        )
    }

    @Test
    fun testApiRootReturnCorrectData() =
        runTest {
            val responseContent = """
                    {
                      "url": "https://blockfrost.io/",
                      "version": "0.1.0"
                    }
                    """
            val healthApi = createHealthApi("/api/v0$PATH_API_ROOT", responseContent)
            val result = healthApi.getApiRoot()
            assertEquals("https://blockfrost.io/", result.url)
            assertEquals("0.1.0", result.version)
        }

    @Test
    fun testApiRootReturn200WithFailData() =
        runTest {
            val healthApi =
                createHealthApiWithFailData("/api/v0$PATH_API_ROOT", HttpStatusCode.OK)
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
            val healthApi =
                createHealthApiWithFailData("/api/v0$PATH_API_ROOT", HttpStatusCode.BadRequest)
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
            val content = """
                    {
                      "is_healthy": true
                    }
                    """
            val healthApi = createHealthApi("/api/v0$PATH_HEALTH", content)
            val result = healthApi.getHealth()
            assertEquals(true, result.isHealthy)
        }

    @Test
    fun testHealthReturn200WithFailData() =
        runTest {
            val healthApi =
                createHealthApiWithFailData("/api/v0$PATH_HEALTH", HttpStatusCode.OK)
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
            val healthApi =
                createHealthApiWithFailData("/api/v0$PATH_HEALTH", HttpStatusCode.BadRequest)
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
            val content = """
                    {
                      "server_time": 1620000000
                    }
                    """
            val healthApi = createHealthApi("/api/v0$PATH_HEALTH_CLOCK", content)
            val result = healthApi.getCurrentBackendTime()
            assertEquals(1620000000L, result.serverTime)
        }

    @Test
    fun testHealthClockReturn200WithFailData() =
        runTest {
            val healthApi =
                createHealthApiWithFailData("/api/v0$PATH_HEALTH_CLOCK", HttpStatusCode.OK)
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
            val healthApi =
                createHealthApiWithFailData("/api/v0$PATH_HEALTH_CLOCK", HttpStatusCode.BadRequest)
            val exception =
                assertFailsWith<BlockfrostException> {
                    healthApi.getCurrentBackendTime()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }
}
