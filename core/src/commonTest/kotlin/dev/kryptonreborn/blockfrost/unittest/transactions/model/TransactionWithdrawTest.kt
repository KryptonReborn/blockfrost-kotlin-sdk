package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.TransactionWithdrawal
import kotlin.test.Test
import kotlin.test.assertEquals

class TransactionWithdrawTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_transaction_withdraw.json".parseFirstElementInArray<TransactionWithdrawal>()

        assertEquals("stake1u9r76ypf5fskppa0cmttas05cgcswrttn6jrq4yd7jpdnvc7gt0yc", content.address)
        assertEquals("431833601", content.amount)
    }
}
