package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.pool.model.StakePoolHistory
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolHistoryTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_stake_pool_history.json".parseFirstElementInArray<StakePoolHistory>()

        assertEquals(233, content.epoch)
        assertEquals(22, content.blocks)
        assertEquals("20485965693569", content.activeStake)
        assertEquals(1.2345, content.activeSize)
        assertEquals(115, content.delegatorsCount)
        assertEquals("206936253674159", content.rewards)
        assertEquals("1290968354", content.fees)
    }
}
