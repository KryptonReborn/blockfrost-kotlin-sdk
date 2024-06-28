package dev.kryptonreborn.blockfrost.unittest.base

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonUnquotedLiteral
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class JsonExtensionsTest {
    @Test
    fun testJsonObjectToMap() {
        val jsonObject =
            buildJsonObject {
                put("key1", "value1")
                put(
                    "key2",
                    buildJsonObject {
                        put("nestedKey", "nestedValue")
                    },
                )
                put(
                    "key3",
                    buildJsonArray {
                        add("arrayValue1")
                        add(
                            buildJsonObject {
                                put("arrayNestedKey", "arrayNestedValue")
                            },
                        )
                    },
                )
            }

        val expectedMap =
            mapOf(
                "key1" to "value1",
                "key2" to mapOf("nestedKey" to "nestedValue"),
                "key3" to
                    listOf(
                        "arrayValue1",
                        mapOf("arrayNestedKey" to "arrayNestedValue"),
                    ),
            )

        assertEquals(expectedMap, jsonObject.deserializeJsonElement())
    }

    @Test
    fun testJsonArrayToList() {
        val jsonArray =
            buildJsonArray {
                add("value1")
                add(1)
                add(1.1)
                add(1F)
                add(1L)
                add(true)
                add(
                    buildJsonObject {
                        put("nestedKey", "nestedValue")
                    },
                )
                add(
                    buildJsonArray {
                        add("nestedArrayValue")
                    },
                )
            }

        val expectedList =
            listOf(
                "value1",
                1,
                1.1,
                1.0,
                1,
                true,
                mapOf("nestedKey" to "nestedValue"),
                listOf("nestedArrayValue"),
            )
        assertEquals(expectedList.toString(), jsonArray.deserializeJsonElement().toString())
    }

    @Test
    fun testJsonElementNormalize() {
        val jsonObject =
            buildJsonObject {
                put("key", "value")
            }
        val jsonArray =
            buildJsonArray {
                add("value")
            }
        val jsonPrimitive = JsonPrimitive("value")

        assertEquals(mapOf("key" to "value"), jsonObject.deserializeJsonElement())
        assertEquals(listOf("value"), jsonArray.deserializeJsonElement())
        assertEquals("value", jsonPrimitive.deserializeJsonElement())
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun testDeserializeUnknownJsonPrimitiveType() {
        val jsonPrimitive = JsonUnquotedLiteral("unknown")

        assertFailsWith<IllegalArgumentException> {
            jsonPrimitive.deserializeJsonElement()
        }
    }
}
