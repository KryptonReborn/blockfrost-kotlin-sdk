package dev.kryptonreborn.blockfrost.excludeBuild

import dev.kryptonreborn.blockfrost.health.HealthApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.core.component.inject

class HealthTest : BaseIntegrationTest() {
    private val healthApi by inject<HealthApi>()

    @Test
    fun testGetHealth() =
        runTest {
            val r = healthApi.getHealth()
            assert(r.isHealthy)
        }

    @Test
    fun testGetApiRoot() =
        runTest {
            val r = healthApi.getApiRoot()
            assert(r.url == "https://blockfrost.io/")
        }

    @Test
    fun testGetCurrentBackendTime() =
        runTest {
            val r = healthApi.getCurrentBackendTime()
            assert(r.serverTime > 0)
        }
}
