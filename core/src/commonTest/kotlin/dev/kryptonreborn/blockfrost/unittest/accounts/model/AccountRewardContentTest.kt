package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountRewardContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountRewardContentTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
               "epoch": 216,
               "amount": "3586329",
               "pool_id": "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy",
                "type": "member"
            }
            """.trimIndent()
        val accountRewardContent = Ktor.json.decodeFromString<AccountRewardContent>(json)
        assertEquals(216, accountRewardContent.epoch)
        assertEquals("3586329", accountRewardContent.amount)
        assertEquals(
            "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy",
            accountRewardContent.poolId,
        )
        assertEquals("member", accountRewardContent.type)
    }
}
