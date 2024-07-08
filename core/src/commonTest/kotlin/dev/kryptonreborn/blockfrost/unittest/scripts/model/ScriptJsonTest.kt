package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.scripts.model.ScriptJson
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class ScriptJsonTest {
    @Test
    fun testDeserialize() {
        val content = "src/commonTest/resources/model/script_json.json".resourceToExpectedData<ScriptJson>()
        println(content)
        assertEquals(content.json as Map<*, *>, mapOf("key" to "value"))
    }

    @Test
    fun testGetJson() {
        val content = ScriptJson(JsonObject(mapOf("key" to JsonPrimitive("value"))))
        assertEquals(mapOf("key" to "value"), content.json)
    }
}
