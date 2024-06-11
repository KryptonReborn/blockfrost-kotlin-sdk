package dev.kryptonreborn.blockfrost.integrationtest

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class HealthIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testHealth() =
        runIntegrationTest {
            val result = blockfrostClient.getHealth()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull()?.isHealthy ?: false)
        }

    @Test
    fun testApiRoot() =
        runIntegrationTest {
            val result = blockfrostClient.getApiRoot()
            assertNotNull(result.getOrNull())
            assertEquals(result.getOrNull()?.url, "https://blockfrost.io/")
        }

    @Test
    fun testCurrentBackendTime() =
        runIntegrationTest {
            val result = blockfrostClient.getCurrentBackendTime()
            assertNotNull(result.getOrNull()?.serverTime, null)
        }
}
