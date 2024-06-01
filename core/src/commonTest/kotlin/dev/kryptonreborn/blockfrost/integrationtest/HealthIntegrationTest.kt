package dev.kryptonreborn.blockfrost.integrationtest

import BlockFrostClient
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.NetworkType
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class HealthIntegrationTest {
    private lateinit var blockfrostClient: BlockFrostClient

    @BeforeTest
    fun setup() {
        blockfrostClient =
            BlockFrostClient(
                blockfrostConfig =
                    BlockfrostConfig(
                        projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ",
                        networkType = NetworkType.Mainnet,
                    ),
            )
    }

    @Test
    fun testHealth() =
        runTest {
            val result = blockfrostClient.getHealth()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull()?.isHealthy ?: false)
        }

    @Test
    fun testApiRoot() =
        runTest {
            val result = blockfrostClient.getApiRoot()
            assertNotNull(result.getOrNull())
            assertEquals(result.getOrNull()?.url, "https://blockfrost.io/")
        }

    @Test
    fun testCurrentBackendTime() =
        runTest {
            val result = blockfrostClient.getCurrentBackendTime()
            assertNotNull(result.getOrNull()?.serverTime, null)
        }
}
