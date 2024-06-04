package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.BaseIntegrationTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MetricsIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testMetrics() =
        runIntegrationTest {
            val result = blockfrostClient.getMetrics()
            assertNotNull(result.getOrNull())
            assertTrue(!result.getOrNull().isNullOrEmpty())
        }

    @Test
    fun testMetricEndpoints() =
        runIntegrationTest {
            val result = blockfrostClient.getMetricEndpoints()
            assertNotNull(result.getOrNull())
            assertTrue(!result.getOrNull().isNullOrEmpty())
        }
}
