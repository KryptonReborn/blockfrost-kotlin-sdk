package dev.kryptonreborn.blockfrost.unittest.metadata.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContent
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TransactionMetadataContentTest {
    @Test
    fun testDeserialize() {
        val content =
            "src/commonTest/resources/list_metadata_content.json".parseFirstElementInArray<TransactionMetadataContent>()
        assertEquals(
            "257d75c8ddb0434e9b63e29ebb6241add2b835a307aa33aedba2effe09ed4ec8",
            content.txHash,
        )
        assertTrue(content.jsonMetadata is Map<*, *>)
        assertTrue((content.jsonMetadata as Map<*, *>)["ADAUSD"] is List<*>)
        val adausdList = (content.jsonMetadata as Map<*, *>)["ADAUSD"] as List<*>
        assertEquals(1, adausdList.size)
        assertTrue(adausdList[0] is Map<*, *>)
        assertEquals("0.10409800535729975", (adausdList[0] as Map<*, *>)["value"])
        assertEquals("ergoOracles", (adausdList[0] as Map<*, *>)["source"])
    }

    @Test
    fun testGetJsonMetadata() {
        val content =
            TransactionMetadataContent(
                "257d75c8ddb0434e9b63e29ebb6241add2b835a307aa33aedba2effe09ed4ec8",
                JsonObject(mapOf("key" to JsonPrimitive("value"))),
            )
        assertEquals(mapOf("key" to "value"), content.jsonMetadata)
    }
}
