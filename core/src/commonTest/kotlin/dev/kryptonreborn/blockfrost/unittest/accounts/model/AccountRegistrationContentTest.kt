package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountRegistrationContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountRegistrationContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_registration.json").readText()
        val accountRegistrationContent =
            Ktor.json.decodeFromString<AccountRegistrationContent>(json)
        assertEquals("tx_hash", accountRegistrationContent.txHash)
        assertEquals("action", accountRegistrationContent.action)
    }
}
