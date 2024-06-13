package dev.kryptonreborn.blockfrost.unittest.assets.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.assets.model.SpecificAsset
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class SpecificAssetTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/specific_asset.json").readText()
        val content = Ktor.json.decodeFromString<SpecificAsset>(json)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            content.asset,
        )
        assertEquals("12000", content.quantity)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            content.asset,
        )
        assertEquals(1, content.mintOrBurnCount)
        assertEquals(
            "6804edf9712d2b619edb6ac86861fe93a730693183a262b165fcc1ba1bc99cad",
            content.initialMintTxHash,
        )
        assertEquals("asset1pkpwyknlvul7az0xx8czhl60pyel45rpje4z8w", content.fingerprint)
        assertEquals("b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a7", content.policyId)
        assertEquals("6e7574636f696e", content.assetName)
        assertEquals(mapOf(), content.onchainMetadata)
        assertEquals("https://www.stakenuts.com/", content.metadata?.url)
        assertEquals("nutcoin", content.metadata?.name)
        assertEquals(6, content.metadata?.decimals)
        assertEquals("The Nut Coin", content.metadata?.description)
        assertEquals("nutc", content.metadata?.ticker)
        assertEquals(
            "logo",
            content.metadata?.logo,
        )
    }
}
