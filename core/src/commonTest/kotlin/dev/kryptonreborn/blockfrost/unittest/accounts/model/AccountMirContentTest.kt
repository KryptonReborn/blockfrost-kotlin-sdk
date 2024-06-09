package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountMirContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountMirContentTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "tx_hash": "tx_hash",
              "amount": "amount"
              }
            """.trimIndent()
        val accountMirContent = Ktor.json.decodeFromString<AccountMirContent>(json)
        assertEquals("tx_hash", accountMirContent.txHash)
        assertEquals("amount", accountMirContent.amount)
    }
}
