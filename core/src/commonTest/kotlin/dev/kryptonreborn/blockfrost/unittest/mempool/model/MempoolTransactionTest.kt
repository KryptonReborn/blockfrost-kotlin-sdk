package dev.kryptonreborn.blockfrost.unittest.mempool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransaction
import kotlin.test.Test
import kotlin.test.assertEquals

class MempoolTransactionTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/mempool_transactions.json".parseFirstElementInArray<MempoolTransaction>()
        assertEquals(
            "1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dce628516157f0",
            content.txHash,
        )
    }
}
