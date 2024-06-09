package dev.kryptonreborn.blockfrost.unittest.accounts.model

import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesAsset
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountAddressesAssetTest {
    @Test
    fun testDeserialization() {
        val json = """{"unit":"lovelace","quantity":"1000"}"""
        val asset = Ktor.json.decodeFromString<AccountAddressesAsset>(json)
        assertEquals("lovelace", asset.unit)
        assertEquals("1000", asset.quantity)
    }
}
