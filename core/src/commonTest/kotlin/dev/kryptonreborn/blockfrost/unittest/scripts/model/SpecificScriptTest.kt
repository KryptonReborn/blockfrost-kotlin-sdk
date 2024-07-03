package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.scripts.model.SpecificScript
import kotlin.test.Test
import kotlin.test.assertEquals

class SpecificScriptTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/model/specific_script.json".resourceToExpectedData<SpecificScript>()

        assertEquals("13a3efd825703a352a8f71f4e2758d08c28c564e8dfcce9f77776ad1", content.scriptHash)
        assertEquals("plutusV1", content.type)
        assertEquals(3119, content.serialisedSize)
    }
}
