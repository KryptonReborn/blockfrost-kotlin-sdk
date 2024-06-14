package dev.kryptonreborn.blockfrost.unittest.assets.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.assets.model.AssetAddress
import kotlin.test.Test
import kotlin.test.assertEquals

class AssetAddressTest {
    @Test
    fun testAssetAddressDeserialization() {
        val assetAddress =
            "src/commonTest/resources/model/asset_address.json".resourceToExpectedData<AssetAddress>()
        assertEquals(
            "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
            assetAddress.address,
        )
        assertEquals("1", assetAddress.quantity)
    }
}
