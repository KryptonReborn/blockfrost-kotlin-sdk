package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountAddressesContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_address.json").readText()
        val content = Ktor.json.decodeFromString<AccountAddressesContent>(json)
        assertEquals("addr1q9", content.address)
    }
}
