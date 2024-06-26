package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContent
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContentCBOR
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataLabel
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoMetadataApiIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testGetTransactionMetadataLabels() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionMetadataLabels()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionMetadataLabel>)
        }

    @Test
    fun testGetTransactionMetadataLabel() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionMetadataContents("1990")
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionMetadataContent>)
        }

    @Test
    fun testGetTransactionMetadataContentCBOR() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionMetadataContentCBOR("1990")
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionMetadataContentCBOR>)
        }
}
