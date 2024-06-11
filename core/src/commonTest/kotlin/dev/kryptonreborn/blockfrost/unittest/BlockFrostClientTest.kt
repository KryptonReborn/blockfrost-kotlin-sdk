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
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_SPECIFIC_ADDRESSES
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_SPECIFIC_ADDRESSES_EXTENDED
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
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
}
