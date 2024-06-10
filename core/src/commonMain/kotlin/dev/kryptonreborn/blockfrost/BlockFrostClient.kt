package dev.kryptonreborn.blockfrost

import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi
import dev.kryptonreborn.blockfrost.accounts.model.AccountQueryParameters
import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.MetricsApi
import io.ktor.client.HttpClient

/**
 * The `BlockFrostClient` class provides a client interface to interact with the Blockfrost API.
 *
 * @property blockfrostConfig The configuration for the Blockfrost API client.
 */
class BlockFrostClient {
    private val httpClient: HttpClient
    private val healthApi: HealthApi
    private val metricsApi: MetricsApi
    private val cardanoAccountsApi: CardanoAccountsApi

    constructor(blockfrostConfig: BlockfrostConfig) {
        this.httpClient = Ktor.httpClient(blockfrostConfig)
        this.healthApi = HealthApi(httpClient)
        this.metricsApi = MetricsApi(httpClient)
        this.cardanoAccountsApi = CardanoAccountsApi(httpClient)
    }

    internal constructor(httpClient: HttpClient) {
        this.httpClient = httpClient
        this.healthApi = HealthApi(httpClient)
        this.metricsApi = MetricsApi(httpClient)
        this.cardanoAccountsApi = CardanoAccountsApi(httpClient)
    }

    /**
     * Retrieves the root information of the API.
     *
     * @return A [Result] containing the root information of the API.
     */
    suspend fun getApiRoot() = handleApiResult { healthApi.getApiRoot() }

    /**
     * Checks the health status of the API.
     *
     * @return A [Result] containing the health status of the API.
     */
    suspend fun getHealth() = handleApiResult { healthApi.getHealth() }

    /**
     * Retrieves the current backend time in UNIX time milliseconds.
     *
     * @return A [Result] containing the current backend time.
     */
    suspend fun getCurrentBackendTime() = handleApiResult { healthApi.getCurrentBackendTime() }

    /**
     * Retrieves the history of your Blockfrost usage metrics for the past 30 days.
     *
     * @return A [Result] containing a list of metrics for the last 30 days.
     */
    suspend fun getMetrics() = handleApiResult { metricsApi.getMetrics() }

    /**
     * Retrieves the history of your Blockfrost usage metrics per endpoint for the past 30 days.
     *
     * @return A [Result] containing a list of metrics per endpoint for the last 30 days.
     */
    suspend fun getMetricEndpoints() = handleApiResult { metricsApi.getMetricEndpoints() }

    /**
     * Retrieves account information for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @return A [Result] containing the account information.
     */
    suspend fun getAccount(stakeAddress: String) = handleApiResult { cardanoAccountsApi.getAccount(stakeAddress) }

    /**
     * Retrieves account rewards for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account rewards.
     */
    suspend fun getAccountRewards(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountRewards(stakeAddress, queryParameters) }

    /**
     * Retrieves account history for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account history.
     */
    suspend fun getAccountHistory(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountHistory(stakeAddress, queryParameters) }

    /**
     * Retrieves account delegations for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account delegations.
     */
    suspend fun getAccountDelegations(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountDelegations(stakeAddress, queryParameters) }

    /**
     * Retrieves account registrations for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account registrations.
     */
    suspend fun getAccountRegistrations(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult {
        cardanoAccountsApi.getAccountRegistrations(
            stakeAddress,
            queryParameters,
        )
    }

    /**
     * Retrieves account withdrawals for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account withdrawals.
     */
    suspend fun getAccountWithdrawals(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountWithdrawals(stakeAddress, queryParameters) }

    /**
     * Retrieves account MIRs (Move Instantaneous Rewards) for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account MIRs.
     */
    suspend fun getAccountMirs(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountMirs(stakeAddress, queryParameters) }

    /**
     * Retrieves account addresses for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account addresses.
     */
    suspend fun getAccountAddresses(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountAddresses(stakeAddress, queryParameters) }

    /**
     * Retrieves account addresses assets for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of account addresses assets.
     */
    suspend fun getAccountAddressesAssets(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult {
        cardanoAccountsApi.getAccountAddressesAssets(
            stakeAddress,
            queryParameters,
        )
    }

    /**
     * Retrieves the total account addresses for a given stake address.
     *
     * @param stakeAddress The stake address to query.
     * @return A [Result] containing the total account addresses.
     */
    suspend fun getAccountAddressesTotal(stakeAddress: String) =
        handleApiResult { cardanoAccountsApi.getAccountAddressesTotal(stakeAddress) }

    /**
     * Handles the result of an API call, wrapping it in a [Result] object.
     *
     * @param block The API call to be executed.
     * @return A [Result] containing the result of the API call or an exception if it fails.
     */
    private inline fun <T> handleApiResult(block: () -> T): Result<T> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
