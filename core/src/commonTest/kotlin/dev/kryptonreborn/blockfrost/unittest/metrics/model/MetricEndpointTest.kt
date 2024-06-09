package dev.kryptonreborn.blockfrost.unittest.metrics.model

import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import kotlin.test.Test
import kotlin.test.assertEquals

class MetricEndpointTest {
    @Test
    fun testDeserialization() {
        val json =
            """
             {
                 "time": 1612543814,
                  "calls": 42,
                 "endpoint": "epoch"
            }
            """.trimIndent()
        val metricEndpoint = Ktor.json.decodeFromString<MetricEndpoint>(json)
        assertEquals(1612543814, metricEndpoint.time)
        assertEquals(42, metricEndpoint.calls)
        assertEquals("epoch", metricEndpoint.endpoint)
    }
}
