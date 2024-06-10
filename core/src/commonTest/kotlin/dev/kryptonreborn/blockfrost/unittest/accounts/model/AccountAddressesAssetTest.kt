package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesAsset
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountAddressesAssetTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_address_asset.json").readText()
        val asset = Ktor.json.decodeFromString<AccountAddressesAsset>(json)
        assertEquals("lovelace", asset.unit)
        assertEquals("1000", asset.quantity)
    }
}
