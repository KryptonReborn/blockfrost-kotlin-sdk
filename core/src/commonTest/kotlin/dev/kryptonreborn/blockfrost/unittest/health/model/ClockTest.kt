package dev.kryptonreborn.blockfrost.unittest.health.model

import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class ClockTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "server_time": 1620000000
            }
            """.trimIndent()
        val clock = Ktor.json.decodeFromString<Clock>(json)
        assertEquals(1620000000, clock.serverTime)
    }
}
