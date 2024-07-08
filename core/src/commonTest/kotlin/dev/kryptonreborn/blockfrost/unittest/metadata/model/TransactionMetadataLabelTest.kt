package dev.kryptonreborn.blockfrost.unittest.metadata.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataLabel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TransactionMetadataLabelTest {
    @Test
    fun testDeserialize() {
        val content =
            "src/commonTest/resources/list_metadata_label.json".parseFirstElementInArray<TransactionMetadataLabel>()
        assertEquals("1990", content.label)
        assertNull(content.cip10)
        assertEquals("1", content.count)
    }
}
