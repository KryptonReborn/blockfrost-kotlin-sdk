package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.ledger.model.BlockchainGenesis
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoLedgerApiIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testGetBlockchainGenesis() =
        runIntegrationTest {
            val result = blockfrostClient.getBlockchainGenesis()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is BlockchainGenesis)
        }
}
