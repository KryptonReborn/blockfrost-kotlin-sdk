package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.pool.model.StakePoolUpdate
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolUpdateTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_stake_pool_update.json".parseFirstElementInArray<StakePoolUpdate>()

        assertEquals("6804edf9712d2b619edb6ac86861fe93a730693183a262b165fcc1ba1bc99cad", content.txHash)
        assertEquals(0, content.certIndex)
        assertEquals("registered", content.action)
    }
}
