package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesAsset
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContentTotal
import dev.kryptonreborn.blockfrost.accounts.model.AccountDelegationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountHistoryContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountMirContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountRegistrationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountRewardContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountWithdrawalContent
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoAccountIntegrationTest : BaseIntegrationTest() {
    private val stakeAddress = "stake1u9ylzsgxaa6xctf4juup682ar3juj85n8tx3hthnljg47zctvm3rc"

    @Test
    fun testAccount() =
        runIntegrationTest {
            val result = blockfrostClient.getAccount(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is AccountContent)
        }

    @Test
    fun testAccountRewards() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountRewards(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountRewardContent>)
        }

    @Test
    fun testAccountHistory() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountHistory(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountHistoryContent>)
        }

    @Test
    fun testAccountDelegations() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountDelegations(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountDelegationContent>)
        }

    @Test
    fun testAccountRegistrations() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountRegistrations(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountRegistrationContent>)
        }

    @Test
    fun testAccountWithdrawals() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountWithdrawals(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountWithdrawalContent>)
        }

    @Test
    fun testAccountMirs() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountMirs(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountMirContent>)
        }

    @Test
    fun testAccountAddresses() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountAddresses(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountAddressesContent>)
        }

    @Test
    fun testAccountAddressesAssets() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountAddressesAssets(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<AccountAddressesAsset>)
        }

    @Test
    fun testAccountAddressesTotal() =
        runIntegrationTest {
            val result = blockfrostClient.getAccountAddressesTotal(stakeAddress)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is AccountContentTotal)
        }
}
