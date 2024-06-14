package dev.kryptonreborn.blockfrost.unittest.assets.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.assets.model.AssetTransaction
import kotlin.test.Test
import kotlin.test.assertEquals

class AssetTransactionTest {
    // test deserialization
    @Test
    fun testDeserialization() {
        val assetTransaction =
            "src/commonTest/resources/model/asset_transaction.json".resourceToExpectedData<AssetTransaction>()
        assertEquals("8788591983aa73981fc92d6cddbbe643959f5a784e84b8bee0db15823f575a5b", assetTransaction.txHash)
        assertEquals(6, assetTransaction.txIndex)
        assertEquals(69, assetTransaction.blockHeight)
        assertEquals(1635505891, assetTransaction.blockTime)
    }
}
