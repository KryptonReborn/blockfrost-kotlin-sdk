package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMIR
import kotlin.test.Test
import kotlin.test.assertEquals

class TransactionMIRTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_transaction_mirs.json".parseFirstElementInArray<TransactionMIR>()

        assertEquals("reserve", content.pot)
        assertEquals(0, content.certIndex)
        assertEquals("stake1u9r76ypf5fskppa0cmttas05cgcswrttn6jrq4yd7jpdnvc7gt0yc", content.address)
        assertEquals("431833601", content.amount)
    }
}
