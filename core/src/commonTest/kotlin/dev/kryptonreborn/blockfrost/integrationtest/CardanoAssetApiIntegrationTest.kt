package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.assets.model.Asset
import dev.kryptonreborn.blockfrost.assets.model.AssetAddress
import dev.kryptonreborn.blockfrost.assets.model.AssetHistory
import dev.kryptonreborn.blockfrost.assets.model.AssetTransaction
import dev.kryptonreborn.blockfrost.assets.model.SpecificAsset
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoAssetApiIntegrationTest : BaseIntegrationTest() {
    private val asset = "00000002df633853f6a47465c9496721d2d5b1291b8398016c0e87ae6e7574636f696e"
    private val policyId = "00000002df633853f6a47465c9496721d2d5b1291b8398016c0e87ae"

    @Test
    fun testGetAssets() =
        runIntegrationTest {
            val result = blockfrostClient.getAssets()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Asset>)
        }

    @Test
    fun testGetSpecificAsset() =
        runIntegrationTest {
            val result = blockfrostClient.getSpecificAsset(asset)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is SpecificAsset)
        }

    @Test
    fun testGetAssetHistory() =
        runIntegrationTest {
            val result = blockfrostClient.getAssetHistory(asset)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AssetHistory>)
        }

    @Test
    fun testGetAssetTxs() =
        runIntegrationTest {
            val result = blockfrostClient.getAssetTxs(asset)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<String>)
        }

    @Test
    fun testGetAssetTransactions() =
        runIntegrationTest {
            val result = blockfrostClient.getAssetTransactions(asset)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AssetTransaction>)
        }

    @Test
    fun testGetAssetAddresses() =
        runIntegrationTest {
            val result = blockfrostClient.getAssetAddresses(asset)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AssetAddress>)
        }

    @Test
    fun testGetAssetPolicy() =
        runIntegrationTest {
            val result = blockfrostClient.getAssetPolicy(policyId)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Asset>)
        }
}
