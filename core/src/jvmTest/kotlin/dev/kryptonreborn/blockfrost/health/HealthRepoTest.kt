package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.base.ApiError
import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

class HealthRepoTest : FunSpec({
    val mockHealthApi = mockk<HealthApi>()
    val healthRepo = HealthRepo(mockHealthApi)

    test("getApiRoot should return correct data when HealthApi returns correct data") {
        runTest {
            val expectedApiRoot = ApiRoot("https://blockfrost.io", "1.0.0")
            coEvery { mockHealthApi.getApiRoot() } returns expectedApiRoot
            val result = healthRepo.getApiRoot()
            result.isRight().shouldBeTrue()
            result.getOrNull() shouldBe expectedApiRoot
        }
    }

    test("getApiRoot should handle exception when HealthApi throws an exception") {
        runTest {
            coEvery { mockHealthApi.getApiRoot() } throws Exception("Error")
            val result = healthRepo.getApiRoot()
            result.isLeft().shouldBeTrue()
        }
    }

    test("getApiRoot should handle ApiError when HealthApi throws ApiError") {
        runTest {
            val expectedErrorResponse = ApiError(400, "Bad Request", "bad_request")
            coEvery { mockHealthApi.getApiRoot() } throws expectedErrorResponse
            val result = healthRepo.getApiRoot()
            result.isLeft().shouldBeTrue()
            result.leftOrNull() shouldBe expectedErrorResponse
        }
    }

    test("getHealth should return correct data when HealthApi returns correct data") {
        runTest {
            val expectedHealth = Health(true)
            coEvery { mockHealthApi.getHealth() } returns expectedHealth
            val result = healthRepo.getHealth()
            result.isRight().shouldBeTrue()
            result.getOrNull() shouldBe expectedHealth
        }
    }

    test("getHealth should handle exception when HealthApi throws an exception") {
        runTest {
            coEvery { mockHealthApi.getHealth() } throws Exception("Error")
            val result = healthRepo.getHealth()
            result.isLeft().shouldBeTrue()
        }
    }

    test("getHealth should handle ApiError when HealthApi throws ApiError") {
        runTest {
            val expectedErrorResponse = ApiError(400, "Bad Request", "bad_request")
            coEvery { mockHealthApi.getHealth() } throws expectedErrorResponse
            val result = healthRepo.getHealth()
            result.isLeft().shouldBeTrue()
            result.leftOrNull() shouldBe expectedErrorResponse
        }
    }

    test("getCurrentBackendTime should return correct data when HealthApi returns correct data") {
        runTest {
            val expectedClock = Clock(1620000000)
            coEvery { mockHealthApi.getCurrentBackendTime() } returns expectedClock
            val result = healthRepo.getCurrentBackendTime()
            result.isRight().shouldBeTrue()
            result.getOrNull() shouldBe expectedClock
        }
    }

    test("getCurrentBackendTime should handle exception when HealthApi throws an exception") {
        runTest {
            coEvery { mockHealthApi.getCurrentBackendTime() } throws Exception("Error")
            val result = healthRepo.getCurrentBackendTime()
            result.isLeft().shouldBeTrue()
        }
    }

    test("getCurrentBackendTime should handle ApiError when HealthApi throws ApiError") {
        runTest {
            val expectedErrorResponse = ApiError(400, "Bad Request", "bad_request")
            coEvery { mockHealthApi.getCurrentBackendTime() } throws expectedErrorResponse
            val result = healthRepo.getCurrentBackendTime()
            result.isLeft().shouldBeTrue()
            result.leftOrNull() shouldBe expectedErrorResponse
        }
    }
})
