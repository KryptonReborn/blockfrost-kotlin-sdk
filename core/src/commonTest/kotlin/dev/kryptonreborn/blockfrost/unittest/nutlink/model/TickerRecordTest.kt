package dev.kryptonreborn.blockfrost.unittest.nutlink.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.nutlink.model.TickerRecord
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TickerRecordTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_ticker_record.json".parseFirstElementInArray<TickerRecord>()

        assertEquals(
            "e8073fd5318ff43eca18a852527166aa8008bee9ee9e891f585612b7e4ba700b",
            content.txHash,
        )
        assertEquals(2657092, content.blockHeight)
        assertEquals(8, content.txIndex)
        assertTrue(content.payload is List<*>)
    }

    @Test
    fun getGetPayload() {
        val content =
            TickerRecord(
                "e8073fd5318ff43eca18a852527166aa8008bee9ee9e891f585612b7e4ba700b",
                2657092,
                8,
                JsonObject(mapOf("key" to JsonPrimitive("value"))),
            )
        assertEquals(mapOf("key" to "value"), content.payload)
    }
}
