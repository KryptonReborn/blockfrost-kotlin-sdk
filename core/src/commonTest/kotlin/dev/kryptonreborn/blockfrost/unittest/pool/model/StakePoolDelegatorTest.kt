package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.pool.model.StakePoolDelegator
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolDelegatorTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_stake_pool_delegator.json".parseFirstElementInArray<StakePoolDelegator>()

        assertEquals("stake1ux4vspfvwuus9uwyp5p3f0ky7a30jq5j80jxse0fr7pa56sgn8kha", content.address)
        assertEquals("1137959159981411", content.liveStake)
    }
}
