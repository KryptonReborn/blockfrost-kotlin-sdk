package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.pool.model.StakePool
import dev.kryptonreborn.blockfrost.pool.model.StakePoolDelegator
import dev.kryptonreborn.blockfrost.pool.model.StakePoolHistory
import dev.kryptonreborn.blockfrost.pool.model.StakePoolInfo
import dev.kryptonreborn.blockfrost.pool.model.StakePoolMetadata
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRelay
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRetire
import dev.kryptonreborn.blockfrost.pool.model.StakePoolUpdate
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoPoolApiIntegrationTest : BaseIntegrationTest() {
    private val poolId = "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy"

    @Test
    fun testGetListStakePools() =
        runIntegrationTest {
            val result = blockfrostClient.getListStakePools()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<String>)
        }

    @Test
    fun testGetListStakePoolsExtended() =
        runIntegrationTest {
            val result = blockfrostClient.getListStakePoolsExtended()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePool>)
        }

    @Test
    fun testGetListRetiredStakePools() =
        runIntegrationTest {
            val result = blockfrostClient.getListRetiredStakePools()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePoolRetire>)
        }

    @Test
    fun testGetListRetiringStakePools() =
        runIntegrationTest {
            val result = blockfrostClient.getListRetiringStakePools()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePoolRetire>)
        }

    @Test
    fun testGetSpecificStakePool() =
        runIntegrationTest {
            val result = blockfrostClient.getSpecificStakePool(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is StakePoolInfo)
        }

    @Test
    fun testGetStakePoolHistory() =
        runIntegrationTest {
            val result = blockfrostClient.getStakePoolHistory(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePoolHistory>)
        }

    @Test
    fun testGetStakePoolMetadata() =
        runIntegrationTest {
            val result = blockfrostClient.getStakePoolMetadata(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is StakePoolMetadata)
        }

    @Test
    fun testGetStakePoolRelays() =
        runIntegrationTest {
            val result = blockfrostClient.getStakePoolRelays(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePoolRelay>)
        }

    @Test
    fun testGetListStakePoolDelegators() =
        runIntegrationTest {
            val result = blockfrostClient.getListStakePoolDelegators(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePoolDelegator>)
        }

    @Test
    fun testGetListStakePoolBlocks() =
        runIntegrationTest {
            val result = blockfrostClient.getListStakePoolBlocks(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<String>)
        }

    @Test
    fun testGetListStakePoolUpdates() =
        runIntegrationTest {
            val result = blockfrostClient.getListStakePoolUpdates(poolId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakePoolUpdate>)
        }
}
