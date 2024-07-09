package dev.kryptonreborn.blockfrost

import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi
import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.ledger.CardanoLedgerApi
import dev.kryptonreborn.blockfrost.mempool.CardanoMempoolApi
import dev.kryptonreborn.blockfrost.metadata.CardanoMetadataApi
import dev.kryptonreborn.blockfrost.metrics.MetricsApi
import dev.kryptonreborn.blockfrost.network.CardanoNetworkApi
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi
import dev.kryptonreborn.blockfrost.utilities.CardanoUtilitiesApi
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
    private val cardanoAddressApi: CardanoAddressApi
    private val cardanoAssetsApi: CardanoAssetsApi
    private val cardanoBlocksApi: CardanoBlocksApi
    private val cardanoEpochsApi: CardanoEpochsApi
    private val cardanoLedgerApi: CardanoLedgerApi
    private val cardanoUtilitiesApi: CardanoUtilitiesApi
    private val cardanoMempoolApi: CardanoMempoolApi
    private val cardanoMetadataApi: CardanoMetadataApi
    private val cardanoNetworkApi: CardanoNetworkApi
    private val cardanoPoolApi: CardanoPoolApi
    private val cardanoScriptsApi: CardanoScriptsApi
    private val cardanoTransactionsApi: CardanoTransactionsApi

    constructor(blockfrostConfig: BlockfrostConfig) : this(Ktor.httpClient(blockfrostConfig))

    // This use for testing
    internal constructor(httpClient: HttpClient) {
        this.httpClient = httpClient
        this.healthApi = HealthApi(httpClient)
        this.metricsApi = MetricsApi(httpClient)
        this.cardanoAccountsApi = CardanoAccountsApi(httpClient)
        this.cardanoAddressApi = CardanoAddressApi(httpClient)
        this.cardanoAssetsApi = CardanoAssetsApi(httpClient)
        this.cardanoBlocksApi = CardanoBlocksApi(httpClient)
        this.cardanoEpochsApi = CardanoEpochsApi(httpClient)
        this.cardanoLedgerApi = CardanoLedgerApi(httpClient)
        this.cardanoUtilitiesApi = CardanoUtilitiesApi(httpClient)
        this.cardanoMempoolApi = CardanoMempoolApi(httpClient)
        this.cardanoMetadataApi = CardanoMetadataApi(httpClient)
        this.cardanoNetworkApi = CardanoNetworkApi(httpClient)
        this.cardanoPoolApi = CardanoPoolApi(httpClient)
        this.cardanoScriptsApi = CardanoScriptsApi(httpClient)
        this.cardanoTransactionsApi = CardanoTransactionsApi(httpClient)
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
        queryParameters: QueryParameters = QueryParameters(),
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
     * Obtain information about a specific address.
     *
     * @param address The address to query.
     * @return A [Result] containing the specific address information.
     */
    suspend fun getSpecificAddress(address: String) = handleApiResult { cardanoAddressApi.getSpecificAddress(address) }

    /**
     * Obtain extended information about a specific address.
     *
     * @param address The address to query.
     * @return A [Result] containing the specific address information.
     */
    suspend fun getSpecificAddressExtended(address: String) = handleApiResult { cardanoAddressApi.getSpecificAddressExtended(address) }

    /**
     * Obtain details about an address.
     *
     * @param address The address to query.
     * @return A [Result] containing the detailed address information.
     */
    suspend fun getAddressDetail(address: String) = handleApiResult { cardanoAddressApi.getAddressDetail(address) }

    /**
     * UTXOs of the address.
     *
     * @param address The address to query.
     * @return A [Result] containing a list of UTXOs.
     */
    suspend fun getAddressUtxos(
        address: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAddressApi.getAddressUtxos(address, queryParameters) }

    /**
     * UTXOs of the address.
     *
     * @param address The address to query.
     * @param asset The asset to query.
     * @return A [Result] containing a list of UTXOs.
     */
    suspend fun getAddressUtxosAssets(
        address: String,
        asset: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAddressApi.getAddressUtxosAssets(address, asset, queryParameters) }

    /**
     * Transactions on the address.
     *
     * @param address The address to query.
     * @return A [Result] containing a list of transactions.
     */
    suspend fun getAddressTransactions(
        address: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAddressApi.getAddressTransactions(address, queryParameters) }

    /**
     * Transactions on the address.
     *
     * @param address The address to query.
     * @return A [Result] containing a list of transactions.
     */
    @Deprecated("This endpoint has been deprecated and may be removed in future versions of the API.")
    suspend fun getAddressTxs(
        address: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAddressApi.getAddressTxs(address, queryParameters) }

    /**
     * List of assets. If an asset is completely burned, it will stay on the list with quantity 0 (order of assets is immutable).
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of assets.
     */
    suspend fun getAssets(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoAssetsApi.getAssets(queryParameters) }

    /**
     * Information about a specific asset
     *
     * @param asset The asset to query.
     * @return A [Result] containing the specific asset information.
     */
    suspend fun getSpecificAsset(asset: String) = handleApiResult { cardanoAssetsApi.getSpecificAsset(asset) }

    /**
     * History of a specific asset
     *
     * @param asset The asset to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of asset history.
     */
    suspend fun getAssetHistory(
        asset: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAssetsApi.getAssetHistory(asset, queryParameters) }

    /**
     * List of a specific asset transactions
     *
     * @param asset The asset to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of asset transactions.
     */
    suspend fun getAssetTxs(
        asset: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAssetsApi.getAssetTxs(asset, queryParameters) }

    /**
     * List of a specific asset transactions
     *
     * @param asset The asset to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of asset transactions.
     */
    suspend fun getAssetTransactions(
        asset: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAssetsApi.getAssetTransactions(asset, queryParameters) }

    /**
     * List of addresses containing a specific asset
     *
     * @param asset The asset to query.
     * @param queryParameters The query parameters to apply.
     */
    suspend fun getAssetAddresses(
        asset: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoAssetsApi.getAssetAddresses(asset, queryParameters) }

    /**
     * List of asset minted under a specific policy
     *
     * @param policyId The policy ID to query.
     * @return A [Result] containing a list of asset minted under a specific policy.
     */
    suspend fun getAssetPolicy(policyId: String) = handleApiResult { cardanoAssetsApi.getAssetPolicy(policyId) }

    /**
     *
     * Return the latest block available to the backends, also known as the tip of the blockchain.
     *
     * @return A [Result] containing the latest block available to the backends.
     */

    suspend fun getLatestBlock() = handleApiResult { cardanoBlocksApi.getLatestBlock() }

    /**
     *
     * Return the transactions within the latest block.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transactions within the latest block.
     */
    suspend fun getLatestBlockTxs(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoBlocksApi.getLatestBlockTxs(queryParameters) }

    /**
     *
     * Return the content of a requested block.
     *
     * @param hashOrNumber The requested block hash or number.
     * @return A [Result] containing the content of a requested block.
     */
    suspend fun getSpecificBlock(hashOrNumber: String) = handleApiResult { cardanoBlocksApi.getSpecificBlock(hashOrNumber) }

    /**
     *
     * Return the list of blocks following a specific block.
     *
     * @param hashOrNumber The requested block hash or number.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of blocks following a specific block.
     */

    suspend fun getNextBlocks(
        hashOrNumber: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoBlocksApi.getNextBlocks(hashOrNumber, queryParameters) }

    /**
     *
     * Return the list of blocks preceding a specific block.
     *
     * @param hashOrNumber The requested block hash or number.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of blocks preceding a specific block.
     */
    suspend fun getPreviousBlocks(
        hashOrNumber: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoBlocksApi.getPreviousBlocks(hashOrNumber, queryParameters) }

    /**
     *
     * Return the content of a requested block for a specific slot.
     *
     * @param slotNumber The requested slot number.
     * @return A [Result] containing the content of a requested block for a specific slot.
     */
    suspend fun getBlockInSlot(slotNumber: Int) = handleApiResult { cardanoBlocksApi.getBlockInSlot(slotNumber) }

    /**
     *
     * Return the content of a requested block for a specific slot in an epoch.
     *
     * @param epochNumber The requested epoch number.
     * @param slotNumber The requested slot number.
     * @return A [Result] containing the content of a requested block for a specific slot in an epoch.
     */

    suspend fun getBlockInSlotInEpoch(
        epochNumber: Int,
        slotNumber: Int,
    ) = handleApiResult { cardanoBlocksApi.getBlockInSlotInEpoch(epochNumber, slotNumber) }

    /**
     *
     * Return the transactions within the block.
     *
     * @param hashOrNumber The requested block hash or number.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transactions within the block.
     */
    suspend fun getBlockTransaction(
        hashOrNumber: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoBlocksApi.getBlockTransaction(hashOrNumber, queryParameters) }

    /**
     *
     * Return list of addresses affected in the specified block with additional information, sorted by the bech32 address, ascending.
     *
     * @param hashOrNumber The requested block hash or number.
     * @return A [Result] containing a list of addresses affected in the specified block.
     */
    suspend fun getAddressAffectedInSpecificBlock(hashOrNumber: String) =
        handleApiResult { cardanoBlocksApi.getAddressAffectedInSpecificBlock(hashOrNumber) }

    /**
     * Return the information about the latest, therefore current, epoch.
     *
     * @return A [Result] containing the data about the epoch
     */
    suspend fun getLatestEpoch() = handleApiResult { cardanoEpochsApi.getLatestEpoch() }

    /**
     * Return the protocol parameters for the latest epoch.
     *
     * @return A [Result] containing the data about the epoch protocol parameters
     */
    suspend fun getLatestEpochProtocolParameters() = handleApiResult { cardanoEpochsApi.getLatestEpochProtocolParameters() }

    /**
     * Return the content of the requested epoch.
     *
     * @param number The epoch number
     * @return A [Result] containing the data about the epoch
     */
    suspend fun getSpecificEpoch(number: Int) = handleApiResult { cardanoEpochsApi.getSpecificEpoch(number) }

    /**
     * Return the list of epochs following a specific epoch.
     *
     * @param number The epoch number
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of epochs following a specific epoch.
     */

    suspend fun getListNextEpochs(
        number: Int,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoEpochsApi.getListNextEpochs(
            number,
            queryParameters,
        )
    }

    /**
     * Return the list of epochs preceding a specific epoch.
     *
     * @param number The epoch number
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of epochs preceding a specific epoch.
     */
    suspend fun getListPreviousEpochs(
        number: Int,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoEpochsApi.getListPreviousEpochs(
            number,
            queryParameters,
        )
    }

    /**
     * Return the active stake distribution for the specified epoch.
     *
     * @param number The epoch number
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing the stake distribution for the epoch.
     */
    suspend fun getStakeDistribution(
        number: Int,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoEpochsApi.getStakeDistribution(
            number,
            queryParameters,
        )
    }

    /**
     * Return the active stake distribution for the epoch specified by stake pool.
     *
     * @param number The epoch number
     * @param poolId The pool ID
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing the stake distribution for the pool in the epoch.
     */
    suspend fun getStakeDistributionPool(
        number: Int,
        poolId: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoEpochsApi.getStakeDistributionPool(
            number,
            poolId,
            queryParameters,
        )
    }

    /**
     * Return the blocks minted for the epoch specified.
     *
     * @param number The epoch number
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing the block distribution for the epoch.
     */
    suspend fun getBlockDistribution(
        number: Int,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoEpochsApi.getBlockDistribution(
            number,
            queryParameters,
        )
    }

    /**
     * Return the block minted for the epoch specified by stake pool.
     *
     * @param number The epoch number
     * @param poolId The pool ID
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing the block distribution for the pool in the epoch.
     */
    suspend fun getBlockDistributionPool(
        number: Int,
        poolId: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoEpochsApi.getBlockDistributionPool(
            number,
            poolId,
            queryParameters,
        )
    }

    /**
     * Return the protocol parameters for the epoch specified.
     *
     * @param number The epoch number
     * @return A [Result] containing the protocol parameters for the epoch.
     */
    suspend fun getProtocolParameters(number: Int) = handleApiResult { cardanoEpochsApi.getProtocolParameters(number) }

    /**
     * Return the information about blockchain genesis.
     *
     * @return A [Result] containing the genesis information of the blockchain.
     */
    suspend fun getBlockchainGenesis() = handleApiResult { cardanoLedgerApi.getBlockchainGenesis() }

    /**
     * Derive Shelley address from an xpub
     *
     * @param xpub Hex xpub
     * @param role Account role
     * @param index Address index
     * @return A [Result] containing the derived address
     */
    suspend fun getDerivedAddress(
        xpub: String,
        role: Int,
        index: Int,
    ) = handleApiResult { cardanoUtilitiesApi.getDerivedAddress(xpub, role, index) }

    /**
     * Submit an already serialized transaction to evaluate how much execution units it requires.
     *
     * @param transaction The transaction to submit, serialized in CBOR.
     * @return A [Result] containing the result of the transaction submission
     */
    suspend fun submitTransactionForExecutionUnitsEvaluation(transaction: String) =
        handleApiResult {
            cardanoUtilitiesApi.submitTransactionForExecutionUnitsEvaluation(transaction)
        }

    /**
     * Return transactions that are currently stored in Blockfrost mempool, waiting to be included in a newly minted block. Shows only transactions submitted via Blockfrost.io.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transactions in the mempool.
     */
    suspend fun getMempool(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoMempoolApi.getMempool(queryParameters) }

    /**
     * Return content of the requested transaction.
     *
     * @param hash The requested transaction hash.
     * @return A [Result] containing the content of the requested transaction.
     */
    suspend fun getMempoolDetails(hash: String) = handleApiResult { cardanoMempoolApi.getMempoolDetails(hash) }

    /**
     * List of mempool transactions where at least one of the transaction inputs or outputs belongs to the address. Shows only transactions submitted via Blockfrost.io.
     *
     * @param address The address to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transactions in the mempool.
     */
    suspend fun getMemPoolByAddress(
        address: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoMempoolApi.getMempoolByAddress(address, queryParameters) }

    /**
     * List of all used transaction metadata labels.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transaction metadata labels.
     */
    suspend fun getTransactionMetadataLabels(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoMetadataApi.getTransactionMetadataLabels(queryParameters) }

    /**
     * Transaction metadata per label.
     *
     * @param label The metadata label to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transaction metadata contents.
     */
    suspend fun getTransactionMetadataContents(
        label: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoMetadataApi.getTransactionMetadataContents(
            label,
            queryParameters,
        )
    }

    /**
     * Transaction metadata per label.
     *
     * @param label The metadata label to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of transaction metadata contents cbor.
     */
    suspend fun getTransactionMetadataContentCBOR(
        label: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult {
        cardanoMetadataApi.getTransactionMetadataContentCBOR(
            label,
            queryParameters,
        )
    }

    /**
     * Return detailed network information.
     *
     * @return A [Result] containing the detailed network information.
     */
    suspend fun getNetworkInformation() = handleApiResult { cardanoNetworkApi.getNetworkInformation() }

    /**
     * Returns start and end of each era along with parameters that can vary between hard forks.
     *
     * @return A [Result] containing the summary of blockchain eras.
     */
    suspend fun querySummaryBlockchainEras() = handleApiResult { cardanoNetworkApi.querySummaryBlockchainEras() }

    /**
     * List of registered stake pools.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of registered stake pools.
     */
    suspend fun getListStakePools(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoPoolApi.getListStakePools(queryParameters) }

    /**
     * List of registered stake pools with additional information.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of registered stake pools with additional information.
     */
    suspend fun getListStakePoolsExtended(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoPoolApi.getListStakePoolsExtended(queryParameters) }

    /**
     * List of already retired pools.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of already retired pools.
     */
    suspend fun getListRetiredStakePools(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoPoolApi.getListRetiredStakePools(queryParameters) }

    /**
     * List of stake pools retiring in the upcoming epochs
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of stake pools retiring in the upcoming epochs.
     */
    suspend fun getListRetiringStakePools(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoPoolApi.getListRetiringStakePools(queryParameters) }

    /**
     * Pool information.
     *
     * @param poolId The pool ID to query.
     * @return A [Result] containing the pool information.
     */
    suspend fun getSpecificStakePool(poolId: String) = handleApiResult { cardanoPoolApi.getSpecificStakePool(poolId) }

    /**
     * History of stake pool parameters over epochs.
     *
     * @param poolId The pool ID to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing the history of stake pool parameters over epochs.
     */
    suspend fun getStakePoolHistory(
        poolId: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoPoolApi.getStakePoolHistory(poolId, queryParameters) }

    /**
     * Stake pool registration metadata.
     *
     * @param poolId The pool ID to query.
     * @return A [Result] containing the stake pool registration metadata.
     */
    suspend fun getStakePoolMetadata(poolId: String) = handleApiResult { cardanoPoolApi.getStakePoolMetadata(poolId) }

    /**
     * Relays of a stake pool.
     *
     * @param poolId The pool ID to query.
     * @return A [Result] containing the relays of a stake pool.
     */
    suspend fun getStakePoolRelays(poolId: String) = handleApiResult { cardanoPoolApi.getStakePoolRelays(poolId) }

    /**
     * List of current stake pools delegators.
     *
     * @param poolId The pool ID to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of current stake pools delegators.
     */
    suspend fun getListStakePoolDelegators(
        poolId: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoPoolApi.getListStakePoolDelegators(poolId, queryParameters) }

    /**
     * List of stake pools blocks.
     *
     * @param poolId The pool ID to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of stake pools blocks.
     */
    suspend fun getListStakePoolBlocks(
        poolId: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoPoolApi.getListStakePoolBlocks(poolId, queryParameters) }

    /**
     * List of certificate updates to the stake pool.
     *
     * @param poolId The pool ID to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of certificate updates to the stake pool.
     */
    suspend fun getListStakePoolUpdates(
        poolId: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoPoolApi.getListStakePoolUpdates(poolId, queryParameters) }

    /**
     * List of scripts.
     *
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of scripts.
     */
    suspend fun getScripts(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { cardanoScriptsApi.getScripts(queryParameters) }

    /**
     * Information about a specific script
     *
     * @param scriptHash The script hash to query.
     * @return A [Result] containing the specific script information.
     */
    suspend fun getScript(scriptHash: String) = handleApiResult { cardanoScriptsApi.getScript(scriptHash) }

    /**
     * JSON representation of a `timelock` script
     *
     * @param scriptHash The script hash to query.
     * @return A [Result] containing the JSON representation of a `timelock` script.
     */
    suspend fun getScriptJson(scriptHash: String) = handleApiResult { cardanoScriptsApi.getScriptJson(scriptHash) }

    /**
     * CBOR representation of a `plutus` script
     *
     * @param scriptHash The script hash to query.
     * @return A [Result] containing the CBOR representation of a `plutus` script.
     */
    suspend fun getScriptCbor(scriptHash: String) = handleApiResult { cardanoScriptsApi.getScriptCbor(scriptHash) }

    /**
     * Query JSON value of a datum by its hash
     *
     * @param datumHash The datum hash to query.
     * @return A [Result] containing the JSON value of a datum.
     */
    suspend fun getScriptDatum(datumHash: String) = handleApiResult { cardanoScriptsApi.getScriptDatum(datumHash) }

    /**
     * Query CBOR serialised datum by its hash
     *
     * @param datumHash The datum hash to query.
     * @return A [Result] containing the CBOR serialised datum.
     */
    suspend fun getScriptDatumCbor(datumHash: String) = handleApiResult { cardanoScriptsApi.getScriptDatumCbor(datumHash) }

    /**
     * List of redeemers of a specific script
     *
     * @param scriptHash The script hash to query.
     * @param queryParameters The query parameters to apply.
     * @return A [Result] containing a list of redeemers of a specific script.
     */
    suspend fun getScriptRedeemers(
        scriptHash: String,
        queryParameters: QueryParameters = QueryParameters(),
    ) = handleApiResult { cardanoScriptsApi.getScriptRedeemers(scriptHash, queryParameters) }

    suspend fun getSpecificTransaction(hash: String) = handleApiResult { cardanoTransactionsApi.getSpecificTransaction(hash) }

    suspend fun getTransactionUtxos(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionUtxos(hash) }

    suspend fun getTransactionStakes(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionStakes(hash) }

    suspend fun getTransactionDelegations(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionDelegations(hash) }

    suspend fun getTransactionWithdrawals(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionWithdrawals(hash) }

    suspend fun getTransactionMirs(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionMirs(hash) }

    suspend fun getTransactionPoolUpdates(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionPoolUpdates(hash) }

    suspend fun getTransactionPoolRetires(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionPoolRetires(hash) }

    suspend fun getTransactionMetadata(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionMetadata(hash) }

    suspend fun getTransactionMetadataCbor(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionMetadataCbor(hash) }

    suspend fun getTransactionRedeemers(hash: String) = handleApiResult { cardanoTransactionsApi.getTransactionRedeemers(hash) }

    suspend fun submitTransaction(hash: String) = handleApiResult { cardanoTransactionsApi.submitTransaction(hash) }

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
