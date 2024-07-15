package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadataCbor
import kotlin.test.Test
import kotlin.test.assertEquals

class TransactionMetadataCborTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_transaction_metadata_cbor.json".parseFirstElementInArray<TransactionMetadataCbor>()

        assertEquals("1968", content.label)
        assertEquals("\\xa100a16b436f6d62696e6174696f6e8601010101010c", content.cborMetadata)
        assertEquals("a100a16b436f6d62696e6174696f6e8601010101010c", content.metadata)
    }
}
