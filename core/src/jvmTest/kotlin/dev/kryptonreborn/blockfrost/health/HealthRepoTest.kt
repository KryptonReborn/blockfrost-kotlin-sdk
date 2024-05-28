package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.base.ApiError
import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HealthRepoTest {
    @Test
    fun testGetApiRootWhenHealthApiReturnCorrect() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            val expectedApiRoot = ApiRoot("https://blockfrost.io", "1.0.0")
            coEvery { mockHealthApi.getApiRoot() } returns expectedApiRoot
            val result = healthRepo.getApiRoot()
            assertTrue(result.isRight())
            assertEquals(result.getOrNull(), expectedApiRoot)
        }

    @Test
    fun testGetApiRootWhenHealthApiThrowException() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            coEvery { mockHealthApi.getApiRoot() } throws Exception("Error")
            val result = healthRepo.getApiRoot()
            assertTrue(result.isLeft())
        }

    @Test
    fun testGetApiRootWhenHealthApiThrowErrorResponse() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            val expectedErrorResponse =
                ApiError(
                    400,
                    "Bad Request",
                    "bad_request",
                )
            coEvery { mockHealthApi.getApiRoot() } throws expectedErrorResponse
            val result = healthRepo.getApiRoot()
            assertTrue(result.isLeft())
            assertEquals(result.leftOrNull(), expectedErrorResponse)
        }

    @Test
    fun testGetHealthWhenHealthApiReturnCorrect() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            val expectedHealth = Health(true)
            coEvery { mockHealthApi.getHealth() } returns expectedHealth
            val result = healthRepo.getHealth()
            assertTrue(result.isRight())
            assertEquals(result.getOrNull(), expectedHealth)
        }

    @Test
    fun testGetHealthWhenHealthApiThrowException() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            coEvery { mockHealthApi.getHealth() } throws Exception("Error")
            val result = healthRepo.getHealth()
            assertTrue(result.isLeft())
        }

    @Test
    fun testGetHealthWhenHealthApiThrowErrorResponse() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            val expectedErrorResponse =
                ApiError(
                    400,
                    "Bad Request",
                    "bad_request",
                )
            coEvery { mockHealthApi.getHealth() } throws expectedErrorResponse
            val result = healthRepo.getHealth()
            assertTrue(result.isLeft())
            assertEquals(result.leftOrNull(), expectedErrorResponse)
        }

    @Test
    fun testGetCurrentBackendTimeWhenHealthApiReturnCorrect() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            val expectedClock = Clock(1620000000)
            coEvery { mockHealthApi.getCurrentBackendTime() } returns expectedClock
            val result = healthRepo.getCurrentBackendTime()
            assertTrue(result.isRight())
            assertEquals(result.getOrNull(), expectedClock)
        }

    @Test
    fun testGetCurrentBackendTimeWhenHealthApiThrowException() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            coEvery { mockHealthApi.getCurrentBackendTime() } throws Exception("Error")
            val result = healthRepo.getCurrentBackendTime()
            assertTrue(result.isLeft())
        }

    @Test
    fun testGetCurrentBackendTimeWhenHealthApiThrowErrorResponse() =
        runTest {
            val mockHealthApi = mockk<HealthApi>()
            val healthRepo = HealthRepo(mockHealthApi)
            val expectedErrorResponse =
                ApiError(
                    400,
                    "Bad Request",
                    "bad_request",
                )
            coEvery { mockHealthApi.getCurrentBackendTime() } throws expectedErrorResponse
            val result = healthRepo.getCurrentBackendTime()
            assertTrue(result.isLeft())
            assertEquals(result.leftOrNull(), expectedErrorResponse)
        }
}
