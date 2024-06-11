package dev.kryptonreborn.blockfrost.unittest.accounts.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountDelegationContent
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountDelegationContentTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/account_delegation.json").readText()
        val accountDelegationContent = Ktor.json.decodeFromString<AccountDelegationContent>(json)
        assertEquals(242, accountDelegationContent.activeEpoch)
        assertEquals("12691385", accountDelegationContent.amount)
        assertEquals(
            "1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dde628516157f0",
            accountDelegationContent.txHash,
        )
        assertEquals(
            "pool1kchver88u3kygsak8wgll7htr8uxn5v35lfrsyy842nkscrzyvj",
            accountDelegationContent.poolId,
        )
    }
}
