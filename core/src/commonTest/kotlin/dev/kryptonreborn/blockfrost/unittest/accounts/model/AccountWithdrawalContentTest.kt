package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountWithdrawalContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountWithdrawalContentTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "tx_hash": "tx_hash",
              "amount": "amount"
              }
            """.trimIndent()
        val accountWithdrawalContent = Ktor.json.decodeFromString<AccountWithdrawalContent>(json)
        assertEquals("tx_hash", accountWithdrawalContent.txHash)
        assertEquals("amount", accountWithdrawalContent.amount)
    }
}
