package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.TransactionRedeemer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TransactionRedeemerTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_transaction_redeemer.json".parseFirstElementInArray<TransactionRedeemer>()

        assertEquals(0, content.txIndex)
        assertEquals("spend", content.purpose)
        assertEquals("ec26b89af41bef0f7585353831cb5da42b5b37185e0c8a526143b824", content.scriptHash)
        assertEquals("923918e403bf43c34b4ef6b48eb2ee04babed17320d8d1b9ff9ad086e86f44ec", content.redeemerDataHash)
        assertEquals("1700", content.unitMem)
        assertEquals("476468", content.unitSteps)
        assertEquals("172033", content.fee)
        assertNull(content.datumHash)
    }
}
