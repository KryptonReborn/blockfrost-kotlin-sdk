package dev.kryptonreborn.blockfrost.excludeBuild

import BlockFrostKotlinSdk
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class HealthTest {
    init {
        BlockFrostKotlinSdk.initConfig(BlockfrostConfig(projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ"))
    }

    @Test
    fun testGetHealth() =
        runTest {
            val r = BlockFrostKotlinSdk.getHealth()
            assertNotNull(r.getOrNull())
            assert(r.getOrNull()?.isHealthy == true)
        }

    @Test
    fun testGetApiRoot() =
        runTest {
            val r = BlockFrostKotlinSdk.getApiRoot()
            assertNotNull(r.getOrNull())
            assert(r.getOrNull()?.url == "https://blockfrost.io/")
        }

    @Test
    fun testGetCurrentBackendTime() =
        runTest {
            val r = BlockFrostKotlinSdk.getCurrentBackendTime()
            assertNotNull(r.getOrNull()?.serverTime)
        }
}
