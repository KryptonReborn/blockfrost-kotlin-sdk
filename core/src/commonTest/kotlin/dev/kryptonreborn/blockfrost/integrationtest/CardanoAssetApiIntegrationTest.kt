package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.assets.model.Asset
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoAssetApiIntegrationTest : BaseIntegrationTest() {
    @Test
    fun testGetAssets() = runIntegrationTest {
        val result = blockfrostClient.getAssets()
        assertNotNull(result.getOrNull())
        println(result.getOrNull())
        assertTrue(result.getOrNull() is List<Asset>)
    }
}
