package dev.kryptonreborn.blockfrost.unittest.metrics.model

import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import kotlin.test.Test
import kotlin.test.assertEquals

class MetricTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "time": 1614523884,
              "calls": 6942
            }
            """.trimIndent()
        val metric = Ktor.json.decodeFromString<Metric>(json)
        assertEquals(1614523884, metric.time)
        assertEquals(6942, metric.calls)
    }
}
