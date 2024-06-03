package dev.kryptonreborn.blockfrost.accounts

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
        httpClient.fetchResource<Account>(
            PATH_ACCOUNTS_STAKE_ADDRESS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountRewards(stakeAddress: String) =
        httpClient.fetchResource<List<Reward>>(
            PATH_ACCOUNTS_REWARDS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountHistory(stakeAddress: String) =
        httpClient.fetchResource<List<History>>(
            PATH_ACCOUNTS_HISTORY.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountDelegations(stakeAddress: String) =
        httpClient.fetchResource<List<Delegation>>(
            PATH_ACCOUNTS_DELEGATIONS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountRegistrations(stakeAddress: String) =
        httpClient.fetchResource<List<Registration>>(
            PATH_ACCOUNTS_REGISTRATIONS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountWithdrawals(stakeAddress: String) =
        httpClient.fetchResource<List<Withdrawal>>(
            PATH_ACCOUNTS_WITHDRAWALS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountMirs(stakeAddress: String) =
        httpClient.fetchResource<List<Mir>>(
            PATH_ACCOUNTS_MIRS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountAddresses(stakeAddress: String) =
        httpClient.fetchResource<List<AccountAddress>>(
            PATH_ACCOUNTS_ADDRESSES.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountAddressesAssets(stakeAddress: String) =
        httpClient.fetchResource<List<AssociatedAsset>>(
            PATH_ACCOUNTS_ADDRESSES_ASSETS.replace(":stake_address", stakeAddress),
        )

    suspend fun getAccountAddressesTotal(stakeAddress: String) =
        httpClient.fetchResource<TotalAddresses>(
            PATH_ACCOUNTS_ADDRESSES_TOTAL.replace(":stake_address", stakeAddress),
        )
}
