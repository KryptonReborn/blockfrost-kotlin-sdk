package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.epochs.model.Epoch
import dev.kryptonreborn.blockfrost.epochs.model.EpochProtocolParameters
import dev.kryptonreborn.blockfrost.epochs.model.StakeInfo
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoEpochApiIntegrationTest : BaseIntegrationTest() {
    private val poolId = "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy"

    @Test
    fun testGetLatestEpoch() =
        runIntegrationTest {
            val result = blockfrostClient.getLatestEpoch()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Epoch)
        }

    @Test
    fun testGetLatestEpochProtocolParameters() =
        runIntegrationTest {
            val result = blockfrostClient.getLatestEpochProtocolParameters()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is EpochProtocolParameters)
        }

    @Test
    fun testGetSpecificEpoch() =
        runIntegrationTest {
            val result = blockfrostClient.getSpecificEpoch(0)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Epoch)
        }

    @Test
    fun testGetListNextEpochs() =
        runIntegrationTest {
            val result = blockfrostClient.getListNextEpochs(0)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Epoch>)
        }

    @Test
    fun testGetListPreviousEpochs() =
        runIntegrationTest {
            val result = blockfrostClient.getListPreviousEpochs(1)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Epoch>)
        }

    @Test
    fun testGetStakeDistribution() =
        runIntegrationTest {
            val result = blockfrostClient.getStakeDistribution(0)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakeInfo>)
        }

    @Test
    fun testGetStakeDistributionPool() =
        runIntegrationTest {
            val result = blockfrostClient.getStakeDistributionPool(0, poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakeInfo>)
        }

    @Test
    fun testGetBlockDistribution() =
        runIntegrationTest {
            val result = blockfrostClient.getBlockDistribution(0)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<String>)
        }

    @Test
    fun testGetBlockDistributionPool() =
        runIntegrationTest {
            val result = blockfrostClient.getBlockDistributionPool(0, poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<String>)
        }

    @Test
    fun testGetProtocolParameters() =
        runIntegrationTest {
            val result = blockfrostClient.getProtocolParameters(491)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is EpochProtocolParameters)
        }
}
