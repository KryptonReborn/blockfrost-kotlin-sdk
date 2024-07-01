package dev.kryptonreborn.blockfrost.unittest.network.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.network.model.EraSummary
import kotlin.test.Test
import kotlin.test.assertEquals

class EraSummaryTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_era_summary.json".parseFirstElementInArray<EraSummary>()
        assertEquals(0, content.start.time)
        assertEquals(0, content.start.slot)
        assertEquals(0, content.start.epoch)
        assertEquals(89856000, content.end.time)
        assertEquals(4492800, content.end.slot)
        assertEquals(208, content.end.epoch)
        assertEquals(21600, content.parameters.epochLength)
        assertEquals(20.0, content.parameters.slotLength)
        assertEquals(4320, content.parameters.safeZone)
    }
}
