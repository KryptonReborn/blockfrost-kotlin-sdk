package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadata
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TransactionMetadataTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_transaction_metadata.json".parseFirstElementInArray<TransactionMetadata>()

        assertEquals("1967", content.label)

        val jsonMetadata = content.jsonMetadata
        assertTrue(jsonMetadata is Map<*, *>)
    }

    @Test
    fun testGetJsonMetadata() {
        val content =
            TransactionMetadata(
                "1966",
                JsonObject(mapOf("key" to JsonPrimitive("value"))),
            )
        assertEquals(mapOf("key" to "value"), content.jsonMetadata)
    }
}
