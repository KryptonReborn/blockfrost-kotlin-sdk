package dev.kryptonreborn.blockfrost.unittest.health.model

import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class HealthTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "is_healthy": true
            }
            """.trimIndent()
        val health = Ktor.json.decodeFromString<Health>(json)
        assertEquals(true, health.isHealthy)
    }
}
