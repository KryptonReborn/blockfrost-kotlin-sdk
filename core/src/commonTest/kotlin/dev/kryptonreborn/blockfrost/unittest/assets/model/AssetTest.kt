package dev.kryptonreborn.blockfrost.unittest.assets.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.assets.model.Asset
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AssetTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/asset.json").readText()
        val content = Ktor.json.decodeFromString<Asset>(json)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            content.asset,
        )
        assertEquals("1", content.quantity)
    }
}
