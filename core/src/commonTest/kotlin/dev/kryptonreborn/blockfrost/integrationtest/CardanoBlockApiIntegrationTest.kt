package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.blocks.model.Block
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoBlockApiIntegrationTest : BaseIntegrationTest() {
    private val hastOrNumber = "63f9730455a55c22d60f2299cb21910f65670d251a45fbc6f958213b6deaecc7"

    @Test
    fun testGetLatestBlock() =
        runIntegrationTest {
            val result = blockfrostClient.getLatestBlock()
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Block)
        }

    @Test
    fun testGetLatestBlockTxs() =
        runIntegrationTest {
            val result = blockfrostClient.getLatestBlockTxs()
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<String>)
        }

    @Test
    fun testGetSpecificBlock() =
        runIntegrationTest {
            val result = blockfrostClient.getSpecificBlock(hastOrNumber)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Block)
        }

    @Test
    fun testGetNextBlocks() =
        runIntegrationTest {
            val result = blockfrostClient.getNextBlocks(hastOrNumber)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Block>)
        }

    @Test
    fun testGetPreviousBlocks() =
        runIntegrationTest {
            val result = blockfrostClient.getPreviousBlocks(hastOrNumber)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Block>)
        }

    @Test
    fun testGetBlockInSlot() =
        runIntegrationTest {
            val result = blockfrostClient.getBlockInSlot(0)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Block)
        }

    @Test
    fun testGetBlockInSlotInEpoch() =
        runIntegrationTest {
            val result = blockfrostClient.getBlockInSlotInEpoch(0, 0)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Block)
        }
}