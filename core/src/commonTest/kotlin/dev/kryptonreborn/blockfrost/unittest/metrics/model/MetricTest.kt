package dev.kryptonreborn.blockfrost.unittest.metrics.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import kotlin.test.Test
import kotlin.test.assertEquals

class MetricTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/metric.json").readText()
        val metric = Ktor.json.decodeFromString<Metric>(json)
        assertEquals(1614523884, metric.time)
        assertEquals(6942, metric.calls)
    }
}
