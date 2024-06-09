package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountRegistrationContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountRegistrationContentTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "tx_hash": "tx_hash",
              "action": "action"
              }
            """.trimIndent()
        val accountRegistrationContent =
            Ktor.json.decodeFromString<AccountRegistrationContent>(json)
        assertEquals("tx_hash", accountRegistrationContent.txHash)
        assertEquals("action", accountRegistrationContent.action)
    }
}
