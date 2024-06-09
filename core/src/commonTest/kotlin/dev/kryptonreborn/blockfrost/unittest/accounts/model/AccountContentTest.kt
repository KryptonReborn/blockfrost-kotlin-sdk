package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertTrue

class AccountContentTest {
    // test account content deserialization
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "stake_address": "stake1ux3g2c9dx2nhhehyrezyxpkstartcqmu9hk63qgfkccw5rqttygt7",
              "active": true,
              "active_epoch": 412,
              "controlled_amount": "619154618165",
              "rewards_sum": "319154618165",
              "withdrawals_sum": "12125369253",
              "reserves_sum": "319154618165",
              "treasury_sum": "12000000",
              "withdrawable_amount": "319154618165",
              "pool_id": "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy"
            }
            """.trimIndent()
        val content = Ktor.json.decodeFromString<AccountContent>(json)
        assertTrue(content.active)
        assertTrue(content.activeEpoch == 412)
        assertTrue(content.controlledAmount == "619154618165")
        assertTrue(content.rewardsSum == "319154618165")
        assertTrue(content.withdrawalsSum == "12125369253")
        assertTrue(content.reservesSum == "319154618165")
        assertTrue(content.treasurySum == "12000000")
        assertTrue(content.withdrawableAmount == "319154618165")
        assertTrue(content.poolId == "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy")
    }
}
