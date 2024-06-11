package dev.kryptonreborn.blockfrost.unittest.health.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class HealthTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/api_health_200.json").readText()
        val health = Ktor.json.decodeFromString<Health>(json)
        assertEquals(true, health.isHealthy)
    }
}
