package dev.kryptonreborn.blockfrost.unittest.epochs.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.epochs.model.Epoch
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class EpochTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/epoch.json").readText()
        val content = Json.decodeFromString<Epoch>(json)
        assertEquals(225, content.epoch)
        assertEquals(1603403091, content.startTime)
        assertEquals(1603835086, content.endTime)
        assertEquals(1603403092, content.firstBlockTime)
        assertEquals(1603835084, content.lastBlockTime)
        assertEquals(21298, content.blockCount)
        assertEquals(17856, content.txCount)
        assertEquals("7849943934049314", content.output)
        assertEquals("4203312194", content.fees)
        assertEquals("784953934049314", content.activeStake)
    }
}
