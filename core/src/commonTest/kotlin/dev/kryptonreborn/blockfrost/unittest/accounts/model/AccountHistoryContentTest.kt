package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountHistoryContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountHistoryContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_history.json").readText()
        val accountHistoryContent = Ktor.json.decodeFromString<AccountHistoryContent>(json)
        assertEquals(211, accountHistoryContent.activeEpoch)
        assertEquals("22695385", accountHistoryContent.amount)
        assertEquals(
            "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy",
            accountHistoryContent.poolId,
        )
    }
}
