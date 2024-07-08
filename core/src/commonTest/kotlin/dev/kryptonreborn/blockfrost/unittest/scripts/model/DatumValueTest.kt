package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.scripts.model.DatumValue
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class DatumValueTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/datum_value.json".resourceToExpectedData<DatumValue>()
        assertEquals(mapOf("int" to 42), content.jsonValue as Map<*, *>)
    }

    @Test
    fun testGetJsonValue() {
        val content =
            DatumValue(
                JsonObject(mapOf("int" to JsonPrimitive(42))),
            )
        assertEquals(mapOf("int" to 42), content.jsonValue)
    }
}
