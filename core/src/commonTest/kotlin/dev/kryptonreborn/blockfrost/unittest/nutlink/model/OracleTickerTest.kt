package dev.kryptonreborn.blockfrost.unittest.nutlink.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.nutlink.model.OracleTicker
import kotlin.test.Test
import kotlin.test.assertEquals

class OracleTickerTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_oracle_ticker.json".parseFirstElementInArray<OracleTicker>()

        assertEquals("ADAUSD", content.name)
        assertEquals(1980038, content.count)
        assertEquals(2657092, content.latestBlock)
    }
}
