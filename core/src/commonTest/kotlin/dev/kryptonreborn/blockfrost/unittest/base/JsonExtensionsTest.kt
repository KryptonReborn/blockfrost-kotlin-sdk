package dev.kryptonreborn.blockfrost.unittest.base

import dev.kryptonreborn.blockfrost.base.normalize
import dev.kryptonreborn.blockfrost.base.toList
import dev.kryptonreborn.blockfrost.base.toMap
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Test
import kotlin.test.assertEquals

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

        assertEquals(expectedMap, jsonObject.toMap())
    }

    @Test
    fun testJsonArrayToList() {
        val jsonArray =
            buildJsonArray {
                add("value1")
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
                mapOf("nestedKey" to "nestedValue"),
                listOf("nestedArrayValue"),
            )

        assertEquals(expectedList, jsonArray.toList())
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

        assertEquals(mapOf("key" to "value"), jsonObject.normalize())
        assertEquals(listOf("value"), jsonArray.normalize())
        assertEquals("value", jsonPrimitive.normalize())
    }
}
