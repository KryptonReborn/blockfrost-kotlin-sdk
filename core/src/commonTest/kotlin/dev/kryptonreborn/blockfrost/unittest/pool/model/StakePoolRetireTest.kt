package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRetire
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolRetireTest {
    @Test
    fun testDeserialization() {
        val resource = "src/commonTest/resources/list_stake_pool_retire.json"
        val content = resource.resourceToExpectedData<List<StakePoolRetire>>()
        assertEquals("pool19u64770wqp6s95gkajc8udheske5e6ljmpq33awxk326zjaza0q", content.first().poolId)
        assertEquals(225, content.first().epoch)
    }
}
