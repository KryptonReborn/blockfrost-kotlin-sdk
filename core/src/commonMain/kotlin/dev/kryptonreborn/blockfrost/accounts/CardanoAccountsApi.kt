package dev.kryptonreborn.blockfrost.accounts

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
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

internal class CardanoAccountsApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_ACCOUNTS_STAKE_ADDRESS = "/api/v0/accounts/:stake_address"
        const val PATH_ACCOUNTS_REWARDS = "/api/v0/accounts/:stake_address/rewards"
        const val PATH_ACCOUNTS_HISTORY = "/api/v0/accounts/:stake_address/history"
        const val PATH_ACCOUNTS_DELEGATIONS = "/api/v0/accounts/:stake_address/delegations"
        const val PATH_ACCOUNTS_REGISTRATIONS = "/api/v0/accounts/:stake_address/registrations"
        const val PATH_ACCOUNTS_WITHDRAWALS = "/api/v0/accounts/:stake_address/withdrawals"
        const val PATH_ACCOUNTS_MIRS = "/api/v0/accounts/:stake_address/mirs"
        const val PATH_ACCOUNTS_ADDRESSES = "/api/v0/accounts/:stake_address/addresses"
        const val PATH_ACCOUNTS_ADDRESSES_ASSETS = "/api/v0/accounts/:stake_address/addresses/assets"
        const val PATH_ACCOUNTS_ADDRESSES_TOTAL = "/api/v0/accounts/:stake_address/addresses/total"
    }

    suspend fun getAccount(stakeAddress: String) =
        httpClient.fetchResource<AccountContent>(
            PATH_ACCOUNTS_STAKE_ADDRESS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountRewards(stakeAddress: String) =
        httpClient.fetchResource<List<AccountRewardContent>>(
            PATH_ACCOUNTS_REWARDS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountHistory(stakeAddress: String) =
        httpClient.fetchResource<List<AccountHistoryContent>>(
            PATH_ACCOUNTS_HISTORY.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountDelegations(stakeAddress: String) =
        httpClient.fetchResource<List<AccountDelegationContent>>(
            PATH_ACCOUNTS_DELEGATIONS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountRegistrations(stakeAddress: String) =
        httpClient.fetchResource<List<AccountRegistrationContent>>(
            PATH_ACCOUNTS_REGISTRATIONS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountWithdrawals(stakeAddress: String) =
        httpClient.fetchResource<List<AccountWithdrawalContent>>(
            PATH_ACCOUNTS_WITHDRAWALS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountMirs(stakeAddress: String) =
        httpClient.fetchResource<List<AccountMirContent>>(
            PATH_ACCOUNTS_MIRS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountAddresses(stakeAddress: String) =
        httpClient.fetchResource<List<AccountAddressesContent>>(
            PATH_ACCOUNTS_ADDRESSES.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountAddressesAssets(stakeAddress: String) =
        httpClient.fetchResource<List<AccountAddressesAsset>>(
            PATH_ACCOUNTS_ADDRESSES_ASSETS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountAddressesTotal(stakeAddress: String) =
        httpClient.fetchResource<AccountContentTotal>(
            PATH_ACCOUNTS_ADDRESSES_TOTAL.replace(":stake_address", stakeAddress),
        )
}
