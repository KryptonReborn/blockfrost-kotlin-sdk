package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountWithdrawalContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountWithdrawalContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_withdrawal.json").readText()
        val accountWithdrawalContent = Ktor.json.decodeFromString<AccountWithdrawalContent>(json)
        assertEquals("tx_hash", accountWithdrawalContent.txHash)
        assertEquals("amount", accountWithdrawalContent.amount)
    }
}
