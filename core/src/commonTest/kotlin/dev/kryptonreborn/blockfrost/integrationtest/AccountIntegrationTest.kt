package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.BaseIntegrationTest
import dev.kryptonreborn.blockfrost.accounts.model.Account
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddress
import dev.kryptonreborn.blockfrost.accounts.model.AssociatedAsset
import dev.kryptonreborn.blockfrost.accounts.model.Delegation
import dev.kryptonreborn.blockfrost.accounts.model.History
import dev.kryptonreborn.blockfrost.accounts.model.Mir
import dev.kryptonreborn.blockfrost.accounts.model.Registration
import dev.kryptonreborn.blockfrost.accounts.model.Reward
import dev.kryptonreborn.blockfrost.accounts.model.TotalAddresses
import dev.kryptonreborn.blockfrost.accounts.model.Withdrawal
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AccountIntegrationTest : BaseIntegrationTest() {
    private val stakeAddress = "stake1u9ylzsgxaa6xctf4juup682ar3juj85n8tx3hthnljg47zctvm3rc"

    @Test
    fun testAccount() =
        runIntegrationTest {
            val result = blockfrostClient.getAccount(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is Account)
        }

    @Test
    fun testAccountRewards() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountRewards(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Reward>)
        }

    @Test
    fun testAccountHistory() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountHistory(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<History>)
        }

    @Test
    fun testAccountDelegations() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountDelegations(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Delegation>)
        }

    @Test
    fun testAccountRegistrations() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountRegistrations(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Registration>)
        }

    @Test
    fun testAccountWithdrawals() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountWithdrawals(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Withdrawal>)
        }

    @Test
    fun testAccountMirs() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountMirs(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Mir>)
        }

    @Test
    fun testAccountAddresses() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountAddresses(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountAddress>)
        }

    @Test
    fun testAccountAddressesAssets() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountAddressesAssets(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AssociatedAsset>)
        }

    @Test
    fun testAccountAddressesTotal() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountAddressesTotal(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is TotalAddresses)
        }
}
