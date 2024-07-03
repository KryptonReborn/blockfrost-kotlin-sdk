package dev.kryptonreborn.blockfrost.unittest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.BlockFrostClient
import dev.kryptonreborn.blockfrost.TestKtorClient.createMockHttpClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES_ASSETS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES_TOTAL
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_DELEGATIONS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_HISTORY
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_MIRS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_REGISTRATIONS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_REWARDS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_STAKE_ADDRESS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_WITHDRAWALS
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
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_DETAIL
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_TRANSACTIONS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_TXS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_UTXOS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_ADDRESS_UTXOS_ASSETS
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_SPECIFIC_ADDRESSES
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi.Companion.PATH_SPECIFIC_ADDRESSES_EXTENDED
import dev.kryptonreborn.blockfrost.addresses.model.AddressDetail
import dev.kryptonreborn.blockfrost.addresses.model.AddressTransaction
import dev.kryptonreborn.blockfrost.addresses.model.AddressUTXO
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_ASSETS
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_ASSET_ADDRESSES
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_ASSET_HISTORY
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_ASSET_POLICY
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_ASSET_TRANSACTION
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_ASSET_TXS
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi.Companion.PATH_SPECIFIC_ASSET
import dev.kryptonreborn.blockfrost.assets.model.Asset
import dev.kryptonreborn.blockfrost.assets.model.AssetAddress
import dev.kryptonreborn.blockfrost.assets.model.AssetHistory
import dev.kryptonreborn.blockfrost.assets.model.AssetTransaction
import dev.kryptonreborn.blockfrost.assets.model.SpecificAsset
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_ADDRESS_AFFECTED
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_BLOCK_IN_SLOT
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_BLOCK_IN_SLOT_IN_EPOCH
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_BLOCK_TRANSACTION
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_LATEST_BLOCK
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_LATEST_BLOCK_TXS
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_NEXT_BLOCKS
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_PREVIOUS_BLOCKS
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi.Companion.PATH_SPECIFIC_BLOCK
import dev.kryptonreborn.blockfrost.blocks.model.BlockAddress
import dev.kryptonreborn.blockfrost.blocks.model.BlockContent
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_BLOCK_DISTRIBUTION
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_BLOCK_DISTRIBUTION_POOL
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_LATEST_EPOCH
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_LIST_NEXT_EPOCHS
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_LIST_PREVIOUS_EPOCHS
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_PROTOCOL_PARAMETERS
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_SPECIFIC_EPOCH
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_STAKE_DISTRIBUTION
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_STAKE_DISTRIBUTION_POOL
import dev.kryptonreborn.blockfrost.epochs.model.Epoch
import dev.kryptonreborn.blockfrost.epochs.model.EpochProtocolParameters
import dev.kryptonreborn.blockfrost.epochs.model.StakeInfo
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_API_ROOT
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH
import dev.kryptonreborn.blockfrost.health.HealthApi.Companion.PATH_HEALTH_CLOCK
import dev.kryptonreborn.blockfrost.ledger.CardanoLedgerApi.Companion.PATH_BLOCKCHAIN_GENESIS
import dev.kryptonreborn.blockfrost.ledger.model.BlockchainGenesis
import dev.kryptonreborn.blockfrost.mempool.CardanoMempoolApi.Companion.PATH_GET_MEMPOOL
import dev.kryptonreborn.blockfrost.mempool.CardanoMempoolApi.Companion.PATH_GET_MEMPOOL_BY_ADDRESS
import dev.kryptonreborn.blockfrost.mempool.CardanoMempoolApi.Companion.PATH_GET_MEMPOOL_DETAIL
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransaction
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransactionDetails
import dev.kryptonreborn.blockfrost.metadata.CardanoMetadataApi.Companion.PATH_GET_TRANSACTION_METADATA_CONTENTS
import dev.kryptonreborn.blockfrost.metadata.CardanoMetadataApi.Companion.PATH_GET_TRANSACTION_METADATA_LABELS
import dev.kryptonreborn.blockfrost.metadata.CardanoMetadataApi.Companion.PATH_GET_TRANSACTION_METADATA_LABEL_CBOR
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContent
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContentCBOR
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataLabel
import dev.kryptonreborn.blockfrost.metrics.MetricsApi.Companion.PATH_METRICS
import dev.kryptonreborn.blockfrost.metrics.MetricsApi.Companion.PATH_METRIC_ENDPOINTS
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import dev.kryptonreborn.blockfrost.network.CardanoNetworkApi.Companion.GET_NETWORK_INFORMATION
import dev.kryptonreborn.blockfrost.network.CardanoNetworkApi.Companion.QUERY_SUMMARY_BLOCKCHAIN_ERAS
import dev.kryptonreborn.blockfrost.network.model.EraSummary
import dev.kryptonreborn.blockfrost.network.model.NetworkInformation
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_RETIRED_STAKE_POOLS
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_RETIRING_STAKE_POOLS
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_STAKE_POOLS
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_STAKE_POOLS_EXTENDED
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_STAKE_POOL_BLOCKS
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_STAKE_POOL_DELEGATORS
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_LIST_STAKE_POOL_UPDATES
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_SPECIFIC_STAKE_POOL
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_STAKE_POOL_HISTORY
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_STAKE_POOL_METADATA
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi.Companion.PATH_STAKE_POOL_RELAYS
import dev.kryptonreborn.blockfrost.pool.model.StakePool
import dev.kryptonreborn.blockfrost.pool.model.StakePoolDelegator
import dev.kryptonreborn.blockfrost.pool.model.StakePoolHistory
import dev.kryptonreborn.blockfrost.pool.model.StakePoolInfo
import dev.kryptonreborn.blockfrost.pool.model.StakePoolMetadata
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRelay
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRetire
import dev.kryptonreborn.blockfrost.pool.model.StakePoolUpdate
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPTS
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_CBOR
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_DATUM
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_DATUM_CBOR
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_JSON
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_REDEEMERS
import dev.kryptonreborn.blockfrost.scripts.model.DatumCborValue
import dev.kryptonreborn.blockfrost.scripts.model.DatumValue
import dev.kryptonreborn.blockfrost.scripts.model.Script
import dev.kryptonreborn.blockfrost.scripts.model.ScriptCbor
import dev.kryptonreborn.blockfrost.scripts.model.ScriptJson
import dev.kryptonreborn.blockfrost.scripts.model.ScriptRedeemer
import dev.kryptonreborn.blockfrost.scripts.model.SpecificScript
import dev.kryptonreborn.blockfrost.utilities.CardanoUtilitiesApi.Companion.PATH_DERIVE_ADDRESS
import dev.kryptonreborn.blockfrost.utilities.CardanoUtilitiesApi.Companion.PATH_SUBMIT_TRANSACTION
import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonObject
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
            val resource = "src/commonTest/resources/api_account_addresses_assets_200.json"
            val expectedData = resource.resourceToExpectedData<List<AccountAddressesAsset>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ACCOUNTS_ADDRESSES_ASSETS.replace(
                        ":stake_address",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAccountAddressesAssets(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAccountAddressesAssetsFail() =
        testApiFail(
            PATH_ACCOUNTS_ADDRESSES_ASSETS.replace(
                ":stake_address",
                anyString,
            ),
        ) { blockFrostClient ->
            blockFrostClient.getAccountAddressesAssets(anyString)
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
                    PATH_ADDRESS_UTXOS_ASSETS.replace(
                        ":address",
                        anyString,
                    ).replace(
                        ":asset",
                        anyString,
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAddressUtxosAssets(anyString, anyString)
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

    @Test
    fun testGetAssets() =
        runTest {
            val resource = "src/commonTest/resources/api_assets_200.json"
            val expectedData = resource.resourceToExpectedData<List<Asset>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ASSETS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAssets()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAssetsFail() = testApiFail(PATH_ASSETS) { blockFrostClient -> blockFrostClient.getAssets() }

    @Test
    fun testGetSpecificAsset() =
        runTest {
            val asset = "asset"
            val resource = "src/commonTest/resources/model/specific_asset.json"
            val expectedData = resource.resourceToExpectedData<SpecificAsset>()
            val httpClient =
                createMockHttpClient(
                    PATH_SPECIFIC_ASSET.replace(":asset", asset),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getSpecificAsset(asset)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetSpecificAssetFail() =
        testApiFail(
            PATH_SPECIFIC_ASSET.replace(":asset", anyString),
        ) { blockFrostClient -> blockFrostClient.getSpecificAsset(anyString) }

    @Test
    fun testGetAssetHistory() =
        runTest {
            val asset = "asset"
            val resource = "src/commonTest/resources/api_asset_histories_200.json"
            val expectedData = resource.resourceToExpectedData<List<AssetHistory>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ASSET_HISTORY.replace(":asset", asset),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAssetHistory(asset)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAssetHistoryFail() =
        testApiFail(
            PATH_ASSET_HISTORY.replace(":asset", anyString),
        ) { blockFrostClient -> blockFrostClient.getAssetHistory(anyString) }

    @Test
    fun testGetAssetTxs() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ASSET_TXS.replace(":asset", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAssetTxs(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAssetTxsFail() =
        testApiFail(
            PATH_ASSET_TXS.replace(":asset", anyString),
        ) { blockFrostClient -> blockFrostClient.getAssetTxs(anyString) }

    @Test
    fun testGetAssetTransactions() =
        runTest {
            val resource = "src/commonTest/resources/api_asset_transactions_200.json"
            val expectedData = resource.resourceToExpectedData<List<AssetTransaction>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ASSET_TRANSACTION.replace(":asset", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAssetTransactions(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAssetTransactionsFail() =
        testApiFail(
            PATH_ASSET_TRANSACTION.replace(":asset", anyString),
        ) { blockFrostClient -> blockFrostClient.getAssetTransactions(anyString) }

    @Test
    fun testGetAssetAddresses() =
        runTest {
            val resource = "src/commonTest/resources/api_asset_addresses_200.json"
            val expectedData = resource.resourceToExpectedData<List<AssetAddress>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ASSET_ADDRESSES.replace(":asset", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAssetAddresses(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAssetAddressesFail() =
        testApiFail(
            PATH_ASSET_ADDRESSES.replace(":asset", anyString),
        ) { blockFrostClient -> blockFrostClient.getAssetAddresses(anyString) }

    @Test
    fun testGetAssetPolicy() =
        runTest {
            val resource = "src/commonTest/resources/api_assets_200.json"
            val expectedData = resource.resourceToExpectedData<List<Asset>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ASSET_POLICY.replace(":policy_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAssetPolicy(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAssetPolicyFail() =
        testApiFail(
            PATH_ASSET_POLICY.replace(":policy_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getAssetPolicy(anyString) }

    @Test
    fun testGetLatestBlock() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val httpClient =
                createMockHttpClient(
                    PATH_LATEST_BLOCK,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getLatestBlock()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetLatestBlockFail() = testApiFail(PATH_LATEST_BLOCK) { blockFrostClient -> blockFrostClient.getLatestBlock() }

    @Test
    fun testGetLatestBlockTxs() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_LATEST_BLOCK_TXS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getLatestBlockTxs()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetLatestBlockTxsFail() = testApiFail(PATH_LATEST_BLOCK_TXS) { blockFrostClient -> blockFrostClient.getLatestBlockTxs() }

    @Test
    fun testGetSpecificBlock() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val httpClient =
                createMockHttpClient(
                    PATH_SPECIFIC_BLOCK.replace(":hash_or_number", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getSpecificBlock(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetSpecificBlockFail() =
        testApiFail(
            PATH_SPECIFIC_BLOCK.replace(":hash_or_number", anyString),
        ) { blockFrostClient -> blockFrostClient.getSpecificBlock(anyString) }

    @Test
    fun testGetNextBlocks() =
        runTest {
            val resource = "src/commonTest/resources/list_block.json"
            val expectedData = resource.resourceToExpectedData<List<BlockContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_NEXT_BLOCKS.replace(":hash_or_number", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getNextBlocks(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetNextBlocksFail() =
        testApiFail(
            PATH_NEXT_BLOCKS.replace(":hash_or_number", anyString),
        ) { blockFrostClient -> blockFrostClient.getNextBlocks(anyString) }

    @Test
    fun testGetPreviousBlocks() =
        runTest {
            val resource = "src/commonTest/resources/list_block.json"
            val expectedData = resource.resourceToExpectedData<List<BlockContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_PREVIOUS_BLOCKS.replace(":hash_or_number", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getPreviousBlocks(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetPreviousBlocksFail() =
        testApiFail(
            PATH_PREVIOUS_BLOCKS.replace(":hash_or_number", anyString),
        ) { blockFrostClient -> blockFrostClient.getPreviousBlocks(anyString) }

    @Test
    fun testGetBlockInSlot() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCK_IN_SLOT.replace(":slot_number", "0"),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getBlockInSlot(0)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetBlockInSlotFail() =
        testApiFail(
            PATH_BLOCK_IN_SLOT.replace(":slot_number", "0"),
        ) { blockFrostClient -> blockFrostClient.getBlockInSlot(0) }

    @Test
    fun testGetBlockInSlotInEpoch() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCK_IN_SLOT_IN_EPOCH.replace(":epoch_number", "0")
                        .replace(":slot_number", "0"),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getBlockInSlotInEpoch(0, 0)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetBlockInSlotInEpochFail() =
        testApiFail(
            PATH_BLOCK_IN_SLOT_IN_EPOCH.replace(":epoch_number", "0")
                .replace(":slot_number", "0"),
        ) { blockFrostClient -> blockFrostClient.getBlockInSlotInEpoch(0, 0) }

    @Test
    fun testGetBlockTransaction() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCK_TRANSACTION.replace(":hash_or_number", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getBlockTransaction(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetBlockTransactionFail() =
        testApiFail(
            PATH_BLOCK_TRANSACTION.replace(":hash_or_number", anyString),
        ) { blockFrostClient -> blockFrostClient.getBlockTransaction(anyString) }

    @Test
    fun testGetAddressAffectedInSpecificBlock() =
        runTest {
            val resource = "src/commonTest/resources/list_block_addresses.json"
            val expectedData = resource.resourceToExpectedData<List<BlockAddress>>()
            val httpClient =
                createMockHttpClient(
                    PATH_ADDRESS_AFFECTED.replace(":hash_or_number", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getAddressAffectedInSpecificBlock(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetAddressAffectedInSpecificBlockFail() =
        testApiFail(
            PATH_ADDRESS_AFFECTED.replace(":hash_or_number", anyString),
        ) { blockFrostClient -> blockFrostClient.getAddressAffectedInSpecificBlock(anyString) }

    @Test
    fun testGetLatestEpoch() =
        runTest {
            val resource = "src/commonTest/resources/model/epoch.json"
            val expectedData = resource.resourceToExpectedData<Epoch>()
            val httpClient =
                createMockHttpClient(
                    PATH_LATEST_EPOCH,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getLatestEpoch()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetLatestEpochFail() = testApiFail(PATH_LATEST_EPOCH) { blockFrostClient -> blockFrostClient.getLatestEpoch() }

    @Test
    fun testGetLatestEpochProtocolParameters() =
        runTest {
            val resource = "src/commonTest/resources/model/protocol_parameters.json"
            val expectedData = resource.resourceToExpectedData<EpochProtocolParameters>()
            val httpClient =
                createMockHttpClient(
                    PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getLatestEpochProtocolParameters()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetLatestEpochProtocolParametersFail() =
        testApiFail(PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS) { blockFrostClient -> blockFrostClient.getLatestEpochProtocolParameters() }

    @Test
    fun testGetSpecificEpoch() =
        runTest {
            val epoch = 1
            val resource = "src/commonTest/resources/model/epoch.json"
            val expectedData = resource.resourceToExpectedData<Epoch>()
            val httpClient =
                createMockHttpClient(
                    PATH_SPECIFIC_EPOCH.replace(":number", epoch.toString()),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getSpecificEpoch(epoch)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetSpecificEpochFail() =
        testApiFail(
            PATH_SPECIFIC_EPOCH.replace(":number", "1"),
        ) { blockFrostClient -> blockFrostClient.getSpecificEpoch(1) }

    @Test
    fun testGetListNextEpochs() =
        runTest {
            val epoch = 1
            val resource = "src/commonTest/resources/list_epochs.json"
            val expectedData = resource.resourceToExpectedData<List<Epoch>>()
            val httpClient =
                createMockHttpClient(
                    PATH_LIST_NEXT_EPOCHS.replace(":number", epoch.toString()),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListNextEpochs(epoch)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetListNextEpochsFail() =
        testApiFail(
            PATH_LIST_NEXT_EPOCHS.replace(":number", "1"),
        ) { blockFrostClient -> blockFrostClient.getListNextEpochs(1) }

    @Test
    fun testGetListPreviousEpochs() =
        runTest {
            val epoch = 1
            val resource = "src/commonTest/resources/list_epochs.json"
            val expectedData = resource.resourceToExpectedData<List<Epoch>>()
            val httpClient =
                createMockHttpClient(
                    PATH_LIST_PREVIOUS_EPOCHS.replace(":number", epoch.toString()),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListPreviousEpochs(epoch)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetListPreviousEpochsFail() =
        testApiFail(
            PATH_LIST_NEXT_EPOCHS.replace(":number", "1"),
        ) { blockFrostClient -> blockFrostClient.getListNextEpochs(1) }

    @Test
    fun testGetStakeDistribution() =
        runTest {
            val epoch = 1
            val resource = "src/commonTest/resources/list_stake_info.json"
            val expectedData = resource.resourceToExpectedData<List<StakeInfo>>()
            val httpClient =
                createMockHttpClient(
                    PATH_STAKE_DISTRIBUTION.replace(":number", epoch.toString()),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getStakeDistribution(epoch)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetStakeDistributionFail() =
        testApiFail(
            PATH_STAKE_DISTRIBUTION.replace(":number", "1"),
        ) { blockFrostClient -> blockFrostClient.getStakeDistribution(1) }

    @Test
    fun testGetStakeDistributionPool() =
        runTest {
            val number = 1
            val pool = "pool"
            val resource = "src/commonTest/resources/list_stake_info.json"
            val expectedData = resource.resourceToExpectedData<List<StakeInfo>>()
            val httpClient =
                createMockHttpClient(
                    PATH_STAKE_DISTRIBUTION_POOL
                        .replace(":number", number.toString())
                        .replace(":pool_id", pool),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getStakeDistributionPool(number, pool)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetStakeDistributionPoolFail() =
        testApiFail(
            PATH_STAKE_DISTRIBUTION_POOL
                .replace(":number", "1")
                .replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getStakeDistributionPool(1, anyString) }

    @Test
    fun testGetBlockDistribution() =
        runTest {
            val epoch = 1
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCK_DISTRIBUTION.replace(":number", epoch.toString()),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getBlockDistribution(epoch)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetBlockDistributionFail() =
        testApiFail(
            PATH_BLOCK_DISTRIBUTION.replace(":number", "1"),
        ) { blockFrostClient -> blockFrostClient.getBlockDistribution(1) }

    @Test
    fun testGetBlockDistributionPool() =
        runTest {
            val number = 1
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCK_DISTRIBUTION_POOL
                        .replace(":number", number.toString())
                        .replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getBlockDistributionPool(number, anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetBlockDistributionPoolFail() =
        testApiFail(
            PATH_BLOCK_DISTRIBUTION_POOL
                .replace(":number", "1")
                .replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getBlockDistributionPool(1, anyString) }

    @Test
    fun testGetProtocolParameters() =
        runTest {
            val epoch = 1
            val resource = "src/commonTest/resources/model/protocol_parameters.json"
            val expectedData = resource.resourceToExpectedData<EpochProtocolParameters>()
            val httpClient =
                createMockHttpClient(
                    PATH_PROTOCOL_PARAMETERS.replace(":number", epoch.toString()),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getProtocolParameters(epoch)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetProtocolParametersFail() =
        testApiFail(
            PATH_PROTOCOL_PARAMETERS.replace(":number", "1"),
        ) { blockFrostClient -> blockFrostClient.getProtocolParameters(1) }

    @Test
    fun testGetBlockchainGenesis() =
        runTest {
            val resource = "src/commonTest/resources/model/blockchain_genesis.json"
            val expectedData = resource.resourceToExpectedData<BlockchainGenesis>()
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCKCHAIN_GENESIS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getBlockchainGenesis()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetBlockchainGenesisFail() =
        testApiFail(
            PATH_BLOCKCHAIN_GENESIS,
        ) { blockFrostClient -> blockFrostClient.getBlockchainGenesis() }

    @Test
    fun testGetDerivedAddress() =
        runTest {
            val resource = "src/commonTest/resources/model/derived_address.json"
            val expectedData = resource.resourceToExpectedData<DerivedAddress>()
            val httpClient =
                createMockHttpClient(
                    PATH_DERIVE_ADDRESS.replace(
                        ":xpub",
                        anyString,
                    ).replace(
                        ":role",
                        "0",
                    ).replace(
                        ":index",
                        "0",
                    ),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getDerivedAddress(anyString, 0, 0)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetDerivedAddressFail() =
        testApiFail(
            PATH_DERIVE_ADDRESS.replace(
                ":xpub",
                anyString,
            ).replace(
                ":role",
                "0",
            ).replace(
                ":index",
                "0",
            ),
        ) { blockFrostClient -> blockFrostClient.getDerivedAddress(anyString, 0, 0) }

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluation() =
        runTest {
            val resource = "src/commonTest/resources/model/any.json"
            val expectedData = resource.resourceToExpectedData<JsonObject>()
            val httpClient =
                createMockHttpClient(
                    PATH_SUBMIT_TRANSACTION,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.submitTransactionForExecutionUnitsEvaluation(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluationFail() =
        testApiFail(
            PATH_SUBMIT_TRANSACTION,
        ) { blockFrostClient ->
            blockFrostClient.submitTransactionForExecutionUnitsEvaluation(
                anyString,
            )
        }

    @Test
    fun testGetMempool() =
        runTest {
            val resource = "src/commonTest/resources/model/mempool_transactions.json"
            val expectedData = resource.resourceToExpectedData<List<MempoolTransaction>>()
            val httpClient = createMockHttpClient(PATH_GET_MEMPOOL, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getMempool()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetMempoolFail() = testApiFail(PATH_GET_MEMPOOL) { blockFrostClient -> blockFrostClient.getMempool() }

    @Test
    fun testGetMempoolDetails() =
        runTest {
            val resource = "src/commonTest/resources/model/mempool_transaction_details.json"
            val expectedData = resource.resourceToExpectedData<MempoolTransactionDetails>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_MEMPOOL_DETAIL.replace(":hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getMempoolDetails(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetMempoolDetailsFail() =
        testApiFail(
            PATH_GET_MEMPOOL_DETAIL.replace(":hash", anyString),
        ) { blockFrostClient -> blockFrostClient.getMempoolDetails(anyString) }

    @Test
    fun getMempoolByAddress() =
        runTest {
            val resource = "src/commonTest/resources/model/mempool_transactions.json"
            val expectedData = resource.resourceToExpectedData<List<MempoolTransaction>>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_MEMPOOL_BY_ADDRESS.replace(":address", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getMemPoolByAddress(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun getMempoolByAddressFail() =
        testApiFail(
            PATH_GET_MEMPOOL_BY_ADDRESS.replace(":address", anyString),
        ) { blockFrostClient -> blockFrostClient.getMemPoolByAddress(anyString) }

    @Test
    fun testGetTransactionMetadataLabels() =
        runTest {
            val resource = "src/commonTest/resources/list_metadata_label.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionMetadataLabel>>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_TRANSACTION_METADATA_LABELS,
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getTransactionMetadataLabels()
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetTransactionMetadataLabelsFail() =
        testApiFail(
            PATH_GET_TRANSACTION_METADATA_LABELS,
        ) { blockFrostClient -> blockFrostClient.getTransactionMetadataLabels() }

    @Test
    fun testGetTransactionMetadataLabel() =
        runTest {
            val resource = "src/commonTest/resources/list_metadata_content.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionMetadataContent>>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_TRANSACTION_METADATA_CONTENTS.replace(":label", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getTransactionMetadataContents(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetTransactionMetadataLabelFail() =
        testApiFail(
            PATH_GET_TRANSACTION_METADATA_CONTENTS.replace(":label", anyString),
        ) { blockFrostClient -> blockFrostClient.getTransactionMetadataContents(anyString) }

    @Test
    fun testGetTransactionMetadataContentCBOR() =
        runTest {
            val resource = "src/commonTest/resources/list_metadata_content_cbor.json"
            val expectedData =
                resource.resourceToExpectedData<List<TransactionMetadataContentCBOR>>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_TRANSACTION_METADATA_LABEL_CBOR
                        .replace(":label", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getTransactionMetadataContentCBOR(anyString)
            assertEquals(expectedData, result.getOrNull())
        }

    @Test
    fun testGetTransactionMetadataContentCBORFail() =
        testApiFail(
            PATH_GET_TRANSACTION_METADATA_LABEL_CBOR.replace(":label", anyString),
        ) { blockFrostClient -> blockFrostClient.getTransactionMetadataContentCBOR(anyString) }

    @Test
    fun testGetNetworkInformation() =
        runTest {
            val resource = "src/commonTest/resources/model/network_information.json"
            val content = resource.resourceToExpectedData<NetworkInformation>()
            val httpClient =
                createMockHttpClient(GET_NETWORK_INFORMATION, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getNetworkInformation()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetNetworkInformationFail() =
        testApiFail(GET_NETWORK_INFORMATION) { blockFrostClient -> blockFrostClient.getNetworkInformation() }

    @Test
    fun testQueryNetworkInformation() =
        runTest {
            val resource = "src/commonTest/resources/list_era_summary.json"
            val content = resource.resourceToExpectedData<List<EraSummary>>()
            val httpClient =
                createMockHttpClient(QUERY_SUMMARY_BLOCKCHAIN_ERAS, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.querySummaryBlockchainEras()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testQueryNetworkInformationFail() =
        testApiFail(QUERY_SUMMARY_BLOCKCHAIN_ERAS) { blockFrostClient -> blockFrostClient.querySummaryBlockchainEras() }

    @Test
    fun testGetListStakePools() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val content = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(PATH_LIST_STAKE_POOLS, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListStakePools()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListStakePoolsFail() = testApiFail(PATH_LIST_STAKE_POOLS) { blockFrostClient -> blockFrostClient.getListStakePools() }

    @Test
    fun testGetListStakePoolsExtended() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool.json"
            val content = resource.resourceToExpectedData<List<StakePool>>()
            val httpClient =
                createMockHttpClient(PATH_LIST_STAKE_POOLS_EXTENDED, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListStakePoolsExtended()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListStakePoolsExtendedFail() =
        testApiFail(PATH_LIST_STAKE_POOLS_EXTENDED) { blockFrostClient -> blockFrostClient.getListStakePoolsExtended() }

    @Test
    fun testGetListRetiredStakePools() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_retire.json"
            val content = resource.resourceToExpectedData<List<StakePoolRetire>>()
            val httpClient =
                createMockHttpClient(PATH_LIST_RETIRED_STAKE_POOLS, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListRetiredStakePools()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListRetiredStakePoolsFail() =
        testApiFail(PATH_LIST_RETIRED_STAKE_POOLS) { blockFrostClient -> blockFrostClient.getListRetiredStakePools() }

    @Test
    fun testGetListRetiringStakePools() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_retire.json"
            val content = resource.resourceToExpectedData<List<StakePoolRetire>>()
            val httpClient =
                createMockHttpClient(PATH_LIST_RETIRING_STAKE_POOLS, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListRetiringStakePools()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListRetiringStakePoolsFail() =
        testApiFail(PATH_LIST_RETIRING_STAKE_POOLS) { blockFrostClient -> blockFrostClient.getListRetiringStakePools() }

    @Test
    fun testGetSpecificStakePool() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_stake_pool.json"
            val content = resource.resourceToExpectedData<StakePoolInfo>()
            val httpClient =
                createMockHttpClient(
                    PATH_SPECIFIC_STAKE_POOL.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getSpecificStakePool(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetSpecificStakePoolFail() =
        testApiFail(
            PATH_SPECIFIC_STAKE_POOL.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getSpecificStakePool(anyString) }

    @Test
    fun testGetStakePoolHistory() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_history.json"
            val content = resource.resourceToExpectedData<List<StakePoolHistory>>()
            val httpClient =
                createMockHttpClient(
                    PATH_STAKE_POOL_HISTORY.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getStakePoolHistory(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetStakePoolHistoryFail() =
        testApiFail(
            PATH_STAKE_POOL_HISTORY.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getStakePoolHistory(anyString) }

    @Test
    fun testGetStakePoolMetadata() =
        runTest {
            val resource = "src/commonTest/resources/model/stake_pool_metadata.json"
            val content = resource.resourceToExpectedData<StakePoolMetadata>()
            val httpClient =
                createMockHttpClient(
                    PATH_STAKE_POOL_METADATA.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getStakePoolMetadata(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetStakePoolMetadataFail() =
        testApiFail(
            PATH_STAKE_POOL_METADATA.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getStakePoolMetadata(anyString) }

    @Test
    fun testGetStakePoolRelays() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_relays.json"
            val content = resource.resourceToExpectedData<List<StakePoolRelay>>()
            val httpClient =
                createMockHttpClient(
                    PATH_STAKE_POOL_RELAYS.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getStakePoolRelays(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetStakePoolRelaysFail() =
        testApiFail(
            PATH_STAKE_POOL_RELAYS.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getStakePoolRelays(anyString) }

    @Test
    fun testGetListStakePoolDelegators() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_delegator.json"
            val content = resource.resourceToExpectedData<List<StakePoolDelegator>>()
            val httpClient =
                createMockHttpClient(
                    PATH_LIST_STAKE_POOL_DELEGATORS.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListStakePoolDelegators(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListStakePoolDelegatorsFail() =
        testApiFail(
            PATH_LIST_STAKE_POOL_DELEGATORS.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getListStakePoolDelegators(anyString) }

    @Test
    fun testGetListStakePoolBlocks() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val content = resource.resourceToExpectedData<List<String>>()
            val httpClient =
                createMockHttpClient(
                    PATH_LIST_STAKE_POOL_BLOCKS.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListStakePoolBlocks(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListStakePoolBlocksFail() =
        testApiFail(
            PATH_LIST_STAKE_POOL_BLOCKS.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getListStakePoolBlocks(anyString) }

    @Test
    fun testGetListStakePoolUpdates() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_update.json"
            val content = resource.resourceToExpectedData<List<StakePoolUpdate>>()
            val httpClient =
                createMockHttpClient(
                    PATH_LIST_STAKE_POOL_UPDATES.replace(":pool_id", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getListStakePoolUpdates(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListStakePoolUpdatesFail() =
        testApiFail(
            PATH_LIST_STAKE_POOL_UPDATES.replace(":pool_id", anyString),
        ) { blockFrostClient -> blockFrostClient.getListStakePoolUpdates(anyString) }

    @Test
    fun testGetScripts() =
        runTest {
            val resource = "src/commonTest/resources/list_scripts.json"
            val content = resource.resourceToExpectedData<List<Script>>()
            val httpClient = createMockHttpClient(PATH_GET_SCRIPTS, Resource(resource).readText())
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScripts()
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptsFail() = testApiFail(PATH_GET_SCRIPTS) { blockFrostClient -> blockFrostClient.getScripts() }

    @Test
    fun testGetScript() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_script.json"
            val content = resource.resourceToExpectedData<SpecificScript>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_SCRIPT.replace(":script_hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScript(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptFail() =
        testApiFail(
            PATH_GET_SCRIPT.replace(
                ":script_hash",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getScript(anyString) }

    @Test
    fun testGetScriptRedeemers() =
        runTest {
            val resource = "src/commonTest/resources/list_script_redeemer.json"
            val content = resource.resourceToExpectedData<List<ScriptRedeemer>>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_SCRIPT_REDEEMERS.replace(":script_hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScriptRedeemers(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptRedeemersFail() =
        testApiFail(
            PATH_GET_SCRIPT_REDEEMERS.replace(
                ":script_hash",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getScriptRedeemers(anyString) }

    @Test
    fun testGetScriptJson() =
        runTest {
            val resource = "src/commonTest/resources/model/script_json.json"
            val content = resource.resourceToExpectedData<ScriptJson>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_SCRIPT_JSON.replace(":script_hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScriptJson(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptJsonFail() =
        testApiFail(
            PATH_GET_SCRIPT_JSON.replace(
                ":script_hash",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getScriptJson(anyString) }

    @Test
    fun testGetScriptCbor() =
        runTest {
            val resource = "src/commonTest/resources/model/script_cbor.json"
            val content = resource.resourceToExpectedData<ScriptCbor>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_SCRIPT_CBOR.replace(":script_hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScriptCbor(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptCborFail() =
        testApiFail(
            PATH_GET_SCRIPT_CBOR.replace(
                ":script_hash",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getScriptCbor(anyString) }

    @Test
    fun testGetScriptDatum() =
        runTest {
            val resource = "src/commonTest/resources/model/datum_value.json"
            val content = resource.resourceToExpectedData<DatumValue>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_SCRIPT_DATUM.replace(":datum_hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScriptDatum(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptDatumFail() =
        testApiFail(
            PATH_GET_SCRIPT_DATUM.replace(
                ":datum_hash",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getScriptDatum(anyString) }

    @Test
    fun testGetScriptDatumCbor() =
        runTest {
            val resource = "src/commonTest/resources/model/datum_cbor_value.json"
            val content = resource.resourceToExpectedData<DatumCborValue>()
            val httpClient =
                createMockHttpClient(
                    PATH_GET_SCRIPT_DATUM_CBOR.replace(":datum_hash", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostClient = BlockFrostClient(httpClient)
            val result = blockFrostClient.getScriptDatumCbor(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetScriptDatumCborFail() =
        testApiFail(
            PATH_GET_SCRIPT_DATUM_CBOR.replace(
                ":datum_hash",
                anyString,
            ),
        ) { blockFrostClient -> blockFrostClient.getScriptDatumCbor(anyString) }

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
