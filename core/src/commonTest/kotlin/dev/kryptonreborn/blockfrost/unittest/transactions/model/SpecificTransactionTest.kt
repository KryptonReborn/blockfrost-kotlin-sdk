package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.transactions.model.SpecificTransaction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SpecificTransactionTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/specific_transaction.json".resourceToExpectedData<SpecificTransaction>()

        assertEquals(
            "1e043f100dce12d107f679685acd2fc0610e10f72a92d412794c9773d11d8477",
            content.hash,
        )
        assertEquals(
            "356b7d7dbb696ccd12775c016941057a9dc70898d87a63fc752271bb46856940",
            content.block,
        )
        assertEquals(123456, content.blockHeight)
        assertEquals(1635505891, content.blockTime)
        assertEquals(42000000, content.slot)
        assertEquals(1, content.index)

        val outputAmount1 = content.outputAmount[0]
        assertEquals("lovelace", outputAmount1.unit)
        assertEquals("42000000", outputAmount1.quantity)

        val outputAmount2 = content.outputAmount[1]
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            outputAmount2.unit,
        )
        assertEquals("12", outputAmount2.quantity)

        assertEquals("182485", content.fees)
        assertEquals("0", content.deposit)
        assertEquals(433, content.size)
        assertNull(content.invalidBefore)
        assertEquals("13885913", content.invalidHereafter)
        assertEquals(4, content.utxoCount)
        assertEquals(0, content.withdrawalCount)
        assertEquals(0, content.mirCertCount)
        assertEquals(0, content.delegationCount)
        assertEquals(0, content.stakeCertCount)
        assertEquals(0, content.poolUpdateCount)
        assertEquals(0, content.poolRetireCount)
        assertEquals(0, content.assetMintOrBurnCount)
        assertEquals(0, content.redeemerCount)
        assertTrue(content.validContract)
    }
}
