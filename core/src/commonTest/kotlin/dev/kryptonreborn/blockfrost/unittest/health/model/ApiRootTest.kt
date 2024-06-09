package dev.kryptonreborn.blockfrost.unittest.health.model

import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiRootTest {
    @Test
    fun testDeserialization() {
        val json =
            """
            {
              "url": "https://blockfrost.io/",
              "version": "0.1.0"
            }
            """.trimIndent()
        val apiRoot = Ktor.json.decodeFromString<ApiRoot>(json)
        assertEquals("https://blockfrost.io/", apiRoot.url)
        assertEquals("0.1.0", apiRoot.version)
    }
}
