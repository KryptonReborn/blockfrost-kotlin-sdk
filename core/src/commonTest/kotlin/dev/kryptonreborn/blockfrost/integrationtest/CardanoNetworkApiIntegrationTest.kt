package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.network.model.EraSummary
import dev.kryptonreborn.blockfrost.network.model.NetworkInformation
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoNetworkApiIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testGetNetworkInformation() {
        runIntegrationTest {
            val result = blockfrostClient.getNetworkInformation()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is NetworkInformation)
        }
    }

    @Test
    fun testQuerySummaryBlockchainEras() {
        runIntegrationTest {
            val result = blockfrostClient.querySummaryBlockchainEras()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<EraSummary>)
        }
    }
}
