package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransaction
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransactionDetails
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoMempoolApiIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testGetMempool() =
        runIntegrationTest {
            val result = blockfrostClient.getMempool()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<MempoolTransaction>)
            result.getOrNull()?.firstOrNull()?.let {
                val resultDetail =
                    blockfrostClient.getMempoolDetails(it.txHash)
                assertNotNull(resultDetail.getOrNull())
                assertTrue(resultDetail.getOrNull() is MempoolTransactionDetails)
            }
        }

    @Test
    fun getMempoolByAddress() =
        runIntegrationTest {
            val result =
                blockfrostClient.getMemPoolByAddress(
                    "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
                )
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<MempoolTransaction>)
        }
}
