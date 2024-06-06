package dev.kryptonreborn.blockfrost.accounts

import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesAsset
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContentTotal
import dev.kryptonreborn.blockfrost.accounts.model.AccountDelegationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountHistoryContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountMirContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountQueryParameters
import dev.kryptonreborn.blockfrost.accounts.model.AccountRegistrationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountRewardContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountWithdrawalContent
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

/**
 * The `CardanoAccountsApi` class provides methods to interact with the Cardano accounts endpoints of the Blockfrost API.
 *
 * @property httpClient The HttpClient instance used to make HTTP requests.
 */
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

    /**
     * Retrieves account information for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @return An [AccountContent] object containing account information.
     */
    suspend fun getAccount(stakeAddress: String) =
        httpClient.fetchResource<AccountContent>(
            PATH_ACCOUNTS_STAKE_ADDRESS.replace(":stake_address", stakeAddress),
        )

    /**
     * Retrieves account rewards for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountRewardContent] objects containing account rewards.
     */
    suspend fun getAccountRewards(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountRewardContent>>(
        PATH_ACCOUNTS_REWARDS.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account history for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountHistoryContent] objects containing account history.
     */
    suspend fun getAccountHistory(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountHistoryContent>>(
        PATH_ACCOUNTS_HISTORY.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account delegations for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountDelegationContent] objects containing account delegations.
     */
    suspend fun getAccountDelegations(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountDelegationContent>>(
        PATH_ACCOUNTS_DELEGATIONS.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account registrations for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountRegistrationContent] objects containing account registrations.
     */
    suspend fun getAccountRegistrations(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountRegistrationContent>>(
        PATH_ACCOUNTS_REGISTRATIONS.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account withdrawals for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountWithdrawalContent] objects containing account withdrawals.
     */
    suspend fun getAccountWithdrawals(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountWithdrawalContent>>(
        PATH_ACCOUNTS_WITHDRAWALS.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account MIRs (Move Instantaneous Rewards) for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountMirContent] objects containing account MIRs.
     */
    suspend fun getAccountMirs(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountMirContent>>(
        PATH_ACCOUNTS_MIRS.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account addresses for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountAddressesContent] objects containing account addresses.
     */
    suspend fun getAccountAddresses(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountAddressesContent>>(
        PATH_ACCOUNTS_ADDRESSES.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves account addresses assets for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A list of [AccountAddressesAsset] objects containing account addresses assets.
     */
    suspend fun getAccountAddressesAssets(
        stakeAddress: String,
        queryParameters: AccountQueryParameters,
    ) = httpClient.fetchResource<List<AccountAddressesAsset>>(
        PATH_ACCOUNTS_ADDRESSES_ASSETS.replace(":stake_address", stakeAddress),
        queryParams = queryParameters.toMap(),
    )

    /**
     * Retrieves the total account addresses for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @return An [AccountContentTotal] object containing the total account addresses.
     */
    suspend fun getAccountAddressesTotal(stakeAddress: String) =
        httpClient.fetchResource<AccountContentTotal>(
            PATH_ACCOUNTS_ADDRESSES_TOTAL.replace(":stake_address", stakeAddress),
        )
}
