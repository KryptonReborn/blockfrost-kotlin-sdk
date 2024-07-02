package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.pool.model.StakePool
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_stake_pool.json".parseFirstElementInArray<StakePool>()
        assertEquals("pool19u64770wqp6s95gkajc8udheske5e6ljmpq33awxk326zjaza0q", content.poolId)
        assertEquals("2f355f79ee007502d116ecb07e36f985b34cebf2d84118f5c6b455a1", content.hex)
        assertEquals("1541200000", content.activeStake)
        assertEquals("1541400000", content.liveStake)
    }
}
