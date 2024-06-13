package dev.kryptonreborn.blockfrost.unittest.assets.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.assets.model.AssetHistory
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AssetHistoryTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/asset_history.json").readText()
        val assetHistory = Ktor.json.decodeFromString<AssetHistory>(json)
        assertEquals(AssetHistory.Action.MINTED, assetHistory.action)
        assertEquals("10", assetHistory.amount)
        assertEquals(
            "2dd15e0ef6e6a17841cb9541c27724072ce4d4b79b91e58432fbaa32d9572531",
            assetHistory.txHash,
        )
    }
}
