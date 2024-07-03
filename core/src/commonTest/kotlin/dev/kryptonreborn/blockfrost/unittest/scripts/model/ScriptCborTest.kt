package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.scripts.model.ScriptCbor
import kotlin.test.Test
import kotlin.test.assertEquals

class ScriptCborTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/script_cbor.json".resourceToExpectedData<ScriptCbor>()

        assertEquals("4e4d01000033222220051200120011", content.cbor)
    }
}
