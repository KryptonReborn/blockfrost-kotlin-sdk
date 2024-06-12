package dev.kryptonreborn.blockfrost.unittest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.BlockFrostClient
import dev.kryptonreborn.blockfrost.TestKtorClient.createMockHttpClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES_TOTAL
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_DELEGATIONS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_HISTORY
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_MIRS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_REGISTRATIONS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_REWARDS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_STAKE_ADDRESS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_WITHDRAWALS
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContentTotal
import dev.kryptonreborn.blockfrost.accounts.model.AccountDelegationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountHistoryContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountMirContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountRegistrationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountRewardContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountWithdrawalContent
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_DETAIL
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_TRANSACTIONS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_TXS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_UTXOS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_SPECIFIC_ADDRESSES
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_SPECIFIC_ADDRESSES_EXTENDED
import dev.kryptonreborn.blockfrost.addresses.model.AddressDetail
import dev.kryptonreborn.blockfrost.addresses.model.AddressTransaction
import dev.kryptonreborn.blockfrost.addresses.model.AddressUTXO
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_API_ROOT
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH_CLOCK
import dev.kryptonreborn.blockfrost.metrics.MetricsApi.Companion.PATH_METRICS
import dev.kryptonreborn.blockfrost.metrics.MetricsApi.Companion.PATH_METRIC_ENDPOINTS
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BlockFrostClientTest {
    private val anyString = "anyString"

    @Test
    fun testGetApiRoot() =
        runTest {
            val httpClient =
                createMockHttpClient(
                    PATH_API_ROOT,
                    Resource("src/commonTest/resources/api_root_200.json").readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getApiRoot()
            assertEquals("https://blockfrost.io/", result.getOrNull()?.url)
            assertEquals("0.1.0", result.getOrNull()?.version)
        }

    @Test
    fun testGetApiRootFail() = testApiFail(PATH_API_ROOT) { blockFrostClient -> blockFrostClient.getApiRoot() }

    @Test
    fun testGetHealth() =
        runTest {
            val httpClient =
                createMockHttpClient(
                    PATH_HEALTH,
                    Resource("src/commonTest/resources/api_health_200.json").readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getHealth()
            assertEquals(true, result.getOrNull()?.isHealthy)
        }

    @Test
    fun testGetHealthFail() = testApiFail(PATH_HEALTH) { blockFrostClient -> blockFrostClient.getHealth() }

    @Test
    fun testGetCurrentBackendTime() =
        runTest {
            val httpClient =
                createMockHttpClient(
                    PATH_HEALTH_CLOCK,
                    Resource("src/commonTest/resources/api_health_clock_200.json").readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getCurrentBackendTime()
            assertEquals(1620000000L, result.getOrNull()?.serverTime)
        }

    @Test
    fun testGetCurrentBackendTimeFail() = testApiFail(PATH_HEALTH_CLOCK) { blockFrostClient -> blockFrostClient.getCurrentBackendTime() }

    @Test
    fun testGetMetrics() =
        runTest {
            val resource = "src/commonTest/resources/api_metrics_200.json"
            val expectedData = resource.resourceToExpectedData<List<Metric>>()
            val httpClient =
                createMockHttpClient(
                    PATH_METRICS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getMetrics()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetMetricsFail() = testApiFail(PATH_METRICS) { blockFrostClient -> blockFrostClient.getMetrics() }

    @Test
    fun getMetricEndpoints() =
        runTest {
            val resource = "src/commonTest/resources/api_metric_endpoints_200.json"
            val expectedData = resource.resourceToExpectedData<List<MetricEndpoint>>()
            val httpClient =
                createMockHttpClient(
                    PATH_METRIC_ENDPOINTS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getMetricEndpoints()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun getMetricEndpointsFail() = testApiFail(PATH_METRIC_ENDPOINTS) { blockFrostClient -> blockFrostClient.getMetricEndpoints() }

    @Test
    fun testGetAccount() =
        runTest {
            val resource = "src/commonTest/resources/api_account_200.json"
            val expectedData = resource.resourceToExpectedData<AccountContent>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_STAKE_ADDRESS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccount(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountFail() =
        testApiFail(
            PATH_ACCOUNTS_STAKE_ADDRESS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAccount(anyString) }

    @Test
    fun testGetAccountRewards() =
        runTest {
            val resource = "src/commonTest/resources/api_account_rewards_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountRewardContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_REWARDS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountRewards(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountRewardsFail() =
        testApiFail(
            PATH_ACCOUNTS_REWARDS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAccountRewards(anyString) }

    @Test
    fun testGetAccountHistory() =
        runTest {
            val resource = "src/commonTest/resources/api_account_histories_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountHistoryContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_HISTORY.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountHistory(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountHistoryFail() =
        testApiFail(
            PATH_ACCOUNTS_HISTORY.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient ->
            blockFrostClient.getAccountHistory(anyString)
        }

    @Test
    fun testGetAccountDelegations() =
        runTest {
            val resource = "src/commonTest/resources/api_account_delegation_history_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountDelegationContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_DELEGATIONS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountDelegations(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountDelegationsFail() =
        testApiFail(
            PATH_ACCOUNTS_DELEGATIONS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAccountDelegations(anyString) }

    @Test
    fun testGetAccountRegistrations() =
        runTest {
            val resource = "src/commonTest/resources/api_account_registrations_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountRegistrationContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_REGISTRATIONS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountRegistrations(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountRegistrationsFail() =
        testApiFail(
            PATH_ACCOUNTS_REGISTRATIONS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAccountRegistrations(anyString) }

    @Test
    fun testGetAccountWithdrawals() =
        runTest {
            val resource = "src/commonTest/resources/api_account_withdrawals_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountWithdrawalContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_WITHDRAWALS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountWithdrawals(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountWithdrawalsFail() =
        testApiFail(
            PATH_ACCOUNTS_WITHDRAWALS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAccountWithdrawals(anyString) }

    @Test
    fun testGetAccountMirs() =
        runTest {
            val resource = "src/commonTest/resources/api_account_mirs_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountMirContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_MIRS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountMirs(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountMirsFail() =
        testApiFail(
            PATH_ACCOUNTS_MIRS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAccountMirs(anyString) }

    @Test
    fun testGetAccountAddresses() =
        runTest {
            val resource = "src/commonTest/resources/api_account_addresses_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountAddressesContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_ADDRESSES.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountAddresses(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountAddressesFail() =
        testApiFail(
            PATH_ACCOUNTS_ADDRESSES.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient ->
            blockFrostClient.getAccountAddresses(anyString)
        }

    @Test
    fun testGetAccountAddressesAssets() =
        runTest {
            val resource = "src/commonTest/resources/api_account_addresses_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountAddressesContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_ADDRESSES.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountAddresses(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountAddressesAssetsFail() =
        testApiFail(
            PATH_ACCOUNTS_ADDRESSES.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient ->
            blockFrostClient.getAccountAddresses(anyString)
        }

    @Test
    fun testGetAccountAddressesTotal() =
        runTest {
            val resource = "src/commonTest/resources/api_account_addresses_total_200.json"
            val expectedData = resource.resourceToExpectedData<AccountContentTotal>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_ADDRESSES_TOTAL.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountAddressesTotal(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountAddressesTotalFail() =
        testApiFail(
            PATH_ACCOUNTS_ADDRESSES_TOTAL.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient ->
            blockFrostClient.getAccountAddressesTotal(anyString)
        }

    @Test
    fun testGetSpecificAddress() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_address.json"
            val expectedData = resource.resourceToExpectedData<SpecificAddress>()
            val httpClient =
                createMockHttpClient(
                    PATH_SPECIFIC_ADDRESSES.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getSpecificAddress(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetSpecificAddressFail() =
        testApiFail(
            PATH_SPECIFIC_ADDRESSES.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getSpecificAddress(anyString) }

    @Test
    fun testGetSpecificAddressExtended() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_address.json"
            val expectedData = resource.resourceToExpectedData<SpecificAddress>()
            val httpClient =
                createMockHttpClient(
                    PATH_SPECIFIC_ADDRESSES_EXTENDED.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getSpecificAddressExtended(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetSpecificAddressExtendedFail() =
        testApiFail(
            PATH_SPECIFIC_ADDRESSES_EXTENDED.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getSpecificAddressExtended(anyString) }

    @Test
    fun testGetAddressDetail() =
        runTest {
            val resource = "src/commonTest/resources/model/address_detail.json"
            val expectedData = resource.resourceToExpectedData<AddressDetail>()
            val httpClient =
                createMockHttpClient(
                    PATH_ADDRESS_DETAIL.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAddressDetail(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAddressDetailFail() =
        testApiFail(
            PATH_ADDRESS_DETAIL.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAddressDetail(anyString) }

    @Test
    fun testGetAddressUtxos() =
        runTest {
            val resource = "src/commonTest/resources/api_address_utxos_200.json"
            val expectedData = resource.resourceToExpectedData<List<AddressUTXO>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ADDRESS_UTXOS.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAddressUtxos(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAddressUtxosFail() =
        testApiFail(
            PATH_ADDRESS_UTXOS.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAddressUtxos(anyString) }

    @Test
    fun testGetAddressUtxosAssets() =
        runTest {
            val resource = "src/commonTest/resources/api_address_utxos_200.json"
            val expectedData = resource.resourceToExpectedData<List<AddressUTXO>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ADDRESS_UTXOS.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAddressUtxos(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAddressUtxosAssetsFail() =
        testApiFail(
            PATH_ADDRESS_UTXOS.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAddressUtxos(anyString) }

    @Test
    fun testGetAddressTransactions() =
        runTest {
            val resource = "src/commonTest/resources/api_address_transactions_200.json"
            val expectedData = resource.resourceToExpectedData<List<AddressTransaction>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ADDRESS_TRANSACTIONS.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAddressTransactions(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAddressTransactionsFail() =
        testApiFail(
            PATH_ADDRESS_TRANSACTIONS.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAddressTransactions(anyString) }

    @Test
    fun testGetAddressTxs() =
        runTest {
            val resource = "src/commonTest/resources/api_address_txs_200.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ADDRESS_TXS.replace(
                        ":address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result =
                blockFrostClient.getAddressTxs(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAddressTxsFail() =
        testApiFail(
            PATH_ADDRESS_TXS.replace(
                ":address",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getAddressTxs(anyString) }

    private fun testApiFail(
        path: String,
        block: suspend (BlockFrostClient) -> Result<*>,
    ) = runTest {
        val httpClient =
            createMockHttpClient(
                path,
                Resource("src/commonTest/resources/test_data_errors_response.json").readText(),
            )
        val blockFrostClient = BlockFrostClient(httpClient)
        val result = block(blockFrostClient)
        assertEquals(null, result.getOrNull())
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is BlockfrostException)
    }
}
