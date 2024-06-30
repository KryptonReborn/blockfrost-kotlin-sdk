package dev.kryptonreborn.blockfrost.unittest.metadata.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContentCBOR
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TransactionMetadataContentCBORTest {
    @Test
    fun testDeserialize() {
        val content =
            "src/commonTest/resources/list_metadata_content_cbor.json".parseFirstElementInArray<TransactionMetadataContentCBOR>()
        assertEquals(
            "257d75c8ddb0434e9b63e29ebb6241add2b835a307aa33aedba2effe09ed4ec8",
            content.txHash,
        )
        assertNull(content.cborMetadata)
        assertNull(content.metadata)
    }
}
