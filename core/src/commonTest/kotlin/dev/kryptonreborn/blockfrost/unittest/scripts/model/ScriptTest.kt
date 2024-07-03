package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.scripts.model.Script
import kotlin.test.Test
import kotlin.test.assertEquals

class ScriptTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_scripts.json".parseFirstElementInArray<Script>()
        assertEquals("13a3efd825703a352a8f71f4e2758d08c28c564e8dfcce9f77776ad1", content.scriptHash)
    }
}
