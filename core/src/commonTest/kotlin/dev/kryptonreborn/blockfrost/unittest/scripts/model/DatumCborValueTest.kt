package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.scripts.model.DatumCborValue
import kotlin.test.Test
import kotlin.test.assertEquals

class DatumCborValueTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/model/datum_cbor_value.json".resourceToExpectedData<DatumCborValue>()
        assertEquals("19a6aa", content.cbor)
    }
}
