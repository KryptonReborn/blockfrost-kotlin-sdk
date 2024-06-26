package dev.kryptonreborn.blockfrost.unittest.metadata.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContent
import kotlinx.serialization.json.JsonObject
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
        assertTrue(content.jsonMetadata is JsonObject)
    }
}
