package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AccountContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/api_account_200.json").readText()
        val content = Ktor.json.decodeFromString<AccountContent>(json)
        assertEquals(
            "stake1ux3g2c9dx2nhhehyrezyxpkstartcqmu9hk63qgfkccw5rqttygt7",
            content.stakeAddress,
        )
        assertTrue(content.active)
        assertEquals(412, content.activeEpoch)
        assertEquals("619154618165", content.controlledAmount)
        assertEquals("319154618165", content.rewardsSum)
        assertEquals("12125369253", content.withdrawalsSum)
        assertEquals("319154618165", content.reservesSum)
        assertEquals("12000000", content.treasurySum)
        assertEquals("319154618165", content.withdrawableAmount)
        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
    }
}
