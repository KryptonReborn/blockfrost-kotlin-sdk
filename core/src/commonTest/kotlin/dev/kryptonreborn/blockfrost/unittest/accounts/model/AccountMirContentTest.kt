package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountMirContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountMirContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_mir.json").readText()
        val accountMirContent = Ktor.json.decodeFromString<AccountMirContent>(json)
        assertEquals("tx_hash", accountMirContent.txHash)
        assertEquals("amount", accountMirContent.amount)
    }
}
