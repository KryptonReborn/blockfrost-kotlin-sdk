import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi
import dev.kryptonreborn.blockfrost.accounts.model.AccountQueryParameters
import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.MetricsApi

/**
 * The `BlockFrostClient` class provides a client interface to interact with the Blockfrost API.
 *
 * @property blockfrostConfig The configuration for the Blockfrost API client.
 */
class BlockFrostClient(blockfrostConfig: BlockfrostConfig) {
    private val httpClient by lazy { Ktor.httpClient(blockfrostConfig) }
    private val healthApi: HealthApi by lazy { HealthApi(httpClient) }
    private val metricsApi: MetricsApi by lazy { MetricsApi(httpClient) }
    private val cardanoAccountsApi: CardanoAccountsApi by lazy { CardanoAccountsApi(httpClient) }

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
     * Handles the result of an API call, wrapping it in a [Result] object.
     *
     * @param block The API call to be executed.
     * @return A [Result] containing the result of the API call or an exception if it fails.
     */
    // Cardano API
    suspend fun getAccount(stakeAddress: String) = handleApiResult { cardanoAccountsApi.getAccount(stakeAddress) }

    suspend fun getAccountRewards(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountRewards(stakeAddress, queryParameters) }

    suspend fun getAccountHistory(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountHistory(stakeAddress, queryParameters) }

    suspend fun getAccountDelegations(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountDelegations(stakeAddress, queryParameters) }

    suspend fun getAccountRegistrations(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult {
        cardanoAccountsApi.getAccountRegistrations(
            stakeAddress,
            queryParameters,
        )
    }

    suspend fun getAccountWithdrawals(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountWithdrawals(stakeAddress, queryParameters) }

    suspend fun getAccountMirs(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountMirs(stakeAddress, queryParameters) }

    suspend fun getAccountAddresses(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult { cardanoAccountsApi.getAccountAddresses(stakeAddress, queryParameters) }

    suspend fun getAccountAddressesAssets(
        stakeAddress: String,
        queryParameters: AccountQueryParameters = AccountQueryParameters(),
    ) = handleApiResult {
        cardanoAccountsApi.getAccountAddressesAssets(
            stakeAddress,
            queryParameters,
        )
    }

    suspend fun getAccountAddressesTotal(stakeAddress: String) =
        handleApiResult { cardanoAccountsApi.getAccountAddressesTotal(stakeAddress) }

    private inline fun <T> handleApiResult(block: () -> T): Result<T> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
