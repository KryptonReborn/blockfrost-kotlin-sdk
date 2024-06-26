package dev.kryptonreborn.blockfrost.unittest.health.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class ClockTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/api_health_clock_200.json").readText()
        val clock = Ktor.json.decodeFromString<Clock>(json)
        assertEquals(1620000000, clock.serverTime)
    }
}
