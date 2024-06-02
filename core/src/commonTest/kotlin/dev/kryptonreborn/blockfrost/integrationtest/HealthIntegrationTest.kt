package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.BaseIntegrationTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class HealthIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testHealth() =
        runTestIfNotCI {
            val result = blockfrostClient.getHealth()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull()?.isHealthy ?: false)
        }

    @Test
    fun testApiRoot() =
        runTestIfNotCI {
            val result = blockfrostClient.getApiRoot()
            assertNotNull(result.getOrNull())
            assertEquals(result.getOrNull()?.url, "https://blockfrost.io/")
        }

    @Test
    fun testCurrentBackendTime() =
        runTestIfNotCI {
            val result = blockfrostClient.getCurrentBackendTime()
            assertNotNull(result.getOrNull()?.serverTime, null)
        }
}
