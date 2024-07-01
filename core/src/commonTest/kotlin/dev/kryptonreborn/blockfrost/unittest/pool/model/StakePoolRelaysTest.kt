package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRelay
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolRelaysTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_stake_pool_relays.json".parseFirstElementInArray<StakePoolRelay>()

        assertEquals("4.4.4.4", content.ipv4)
        assertEquals("https://stakenuts.com/mainnet.json", content.ipv6)
        assertEquals("relay1.stakenuts.com", content.dns)
        assertEquals("_relays._tcp.relays.stakenuts.com", content.dnsSrv)
        assertEquals(3001, content.port)
    }
}
