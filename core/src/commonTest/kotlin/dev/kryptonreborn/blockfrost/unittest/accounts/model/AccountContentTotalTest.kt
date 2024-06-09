package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountContentTotal
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountContentTotalTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "stake_address": "stake1u9l5q5jwgelgagzyt6nuaasefgmn8pd25c8e9qpeprq0tdcp0e3uk",
              "received_sum": [
                {
                  "unit": "lovelace",
                  "quantity": "42000000"
                },
                {
                  "unit": "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
                  "quantity": "12"
                }
              ],
              "sent_sum": [
                {
                  "unit": "lovelace",
                  "quantity": "42000000"
                },
                {
                  "unit": "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
                  "quantity": "12"
                }
              ],
              "tx_count": 12
            }
            """.trimIndent()
        val accountContentTotal = Ktor.json.decodeFromString<AccountContentTotal>(json)
        assertEquals(
            "stake1u9l5q5jwgelgagzyt6nuaasefgmn8pd25c8e9qpeprq0tdcp0e3uk",
            accountContentTotal.stakeAddress,
        )
        assertEquals(2, accountContentTotal.receivedSum.size)
        assertEquals("lovelace", accountContentTotal.receivedSum[0].unit)
        assertEquals("42000000", accountContentTotal.receivedSum[0].quantity)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            accountContentTotal.receivedSum[1].unit,
        )
        assertEquals("12", accountContentTotal.receivedSum[1].quantity)
        assertEquals(2, accountContentTotal.sentSum.size)
        assertEquals("lovelace", accountContentTotal.sentSum[0].unit)
        assertEquals("42000000", accountContentTotal.sentSum[0].quantity)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            accountContentTotal.sentSum[1].unit,
        )
        assertEquals("12", accountContentTotal.sentSum[1].quantity)
        assertEquals(12, accountContentTotal.txCount)
    }
}
