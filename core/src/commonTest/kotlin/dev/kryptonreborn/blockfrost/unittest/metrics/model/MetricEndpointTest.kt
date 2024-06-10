package dev.kryptonreborn.blockfrost.unittest.metrics.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import kotlin.test.Test
import kotlin.test.assertEquals

class MetricEndpointTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/api_metric_endpoints_200.json").readText()
        val metricEndpoint = Ktor.json.decodeFromString<MetricEndpoint>(json)
        assertEquals(1612543814, metricEndpoint.time)
        assertEquals(42, metricEndpoint.calls)
        assertEquals("epoch", metricEndpoint.endpoint)
    }
}
