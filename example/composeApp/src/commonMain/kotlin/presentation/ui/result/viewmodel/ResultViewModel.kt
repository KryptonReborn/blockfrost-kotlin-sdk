package presentation.ui.result.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kryptonreborn.blockfrost.BlockFrostClient
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.BlockfrostLogLevel
import dev.kryptonreborn.blockfrost.NetworkType
import kotlinx.coroutines.launch

class ResultViewModel : ViewModel() {
    private val stakeAddress = "stake1u9ylzsgxaa6xctf4juup682ar3juj85n8tx3hthnljg47zctvm3rc"
    private val address =
        "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz"
    private val asset = "00000002df633853f6a47465c9496721d2d5b1291b8398016c0e87ae6e7574636f696e"
    private val policyId = "00000002df633853f6a47465c9496721d2d5b1291b8398016c0e87ae"
    private val hastOrNumber = "63f9730455a55c22d60f2299cb21910f65670d251a45fbc6f958213b6deaecc7"
    private val poolId = "pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy"
    private val epoch = 491
    private val xpub =
        "d507c8f866691bd96e131334c355188b1a1d0b2fa0ab11545075aab332d77d9eb19657ad13ee581b56b0f8d744d66ca356b93d42fe176b3de007d53e9c4c4e7a"
    private val cbor =
        "84a500818258205d4e2439244dc018d33e0ccb7521cda8f112273a49aeb6f412675b70d88599" +
            "cd010186825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa0" +
            "8b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a069db9c0825839003dd93bd0a5d1dd87b6413484b63a" +
            "d1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a6" +
            "3339a3a825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08" +
            "b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a319b2c5f825839003dd93bd0a5d1dd87b6413484b63ad" +
            "1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a18" +
            "cd9630825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b" +
            "2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a18cd9630825839003dd93bd0a5d1dd87b6413484b63ad1" +
            "b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a004c4b40021a0002be85031a031e00790800a0f5f6"
    private val scriptHash = "65c197d565e88a20885e535f93755682444d3c02fd44dd70883fe89e"
    private val datumHash = "db583ad85881a96c73fbb26ab9e24d1120bb38f45385664bb9c797a2ea8d9a2d"
    private val hashTransaction = "37746d2fa855de3095792d2e534deea9f1dbb43a113eec5d1dfad3963d8bb09d"
    private val blockFrostClient =
        BlockFrostClient(
            BlockfrostConfig(
                networkType = NetworkType.Mainnet,
                projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ",
                logLevel = BlockfrostLogLevel.ALL,
            ),
        )
    val state: MutableState<ResultState> = mutableStateOf(ResultState())

    fun onTriggerEvent(event: ResultEvent) {
        viewModelScope.launch {
            when (event) {
                ResultEvent.GetApiRoot ->
                    getResponse {
                        blockFrostClient.getApiRoot()
                    }

                ResultEvent.GetCurrentBackendTime ->
                    getResponse {
                        blockFrostClient.getCurrentBackendTime()
                    }

                ResultEvent.GetHealth ->
                    getResponse {
                        blockFrostClient.getHealth()
                    }

                ResultEvent.GetMetrics ->
                    getResponse {
                        blockFrostClient.getMetrics()
                    }

                ResultEvent.GetMetricEndpoints ->
                    getResponse {
                        blockFrostClient.getMetricEndpoints()
                    }

                ResultEvent.GetAccount -> {
                    getResponse {
                        blockFrostClient.getAccount(stakeAddress)
                    }
                }

                ResultEvent.GetAccountAddressesAsset -> {
                    getResponse {
                        blockFrostClient.getAccountAddressesAssets(stakeAddress)
                    }
                }

                ResultEvent.GetAccountAddresses -> {
                    getResponse {
                        blockFrostClient.getAccountAddresses(stakeAddress)
                    }
                }

                ResultEvent.GetAccountAddressesTotal -> {
                    getResponse {
                        blockFrostClient.getAccountAddressesTotal(stakeAddress)
                    }
                }

                ResultEvent.GetAccountDelegations -> {
                    getResponse {
                        blockFrostClient.getAccountDelegations(stakeAddress)
                    }
                }

                ResultEvent.GetAccountHistory -> {
                    getResponse {
                        blockFrostClient.getAccountHistory(stakeAddress)
                    }
                }

                ResultEvent.GetAccountMirs -> {
                    getResponse {
                        blockFrostClient.getAccountMirs(stakeAddress)
                    }
                }

                ResultEvent.GetAccountRegistrations -> {
                    getResponse {
                        blockFrostClient.getAccountRegistrations(stakeAddress)
                    }
                }

                ResultEvent.GetAccountRewards -> {
                    getResponse {
                        blockFrostClient.getAccountRewards(stakeAddress)
                    }
                }

                ResultEvent.GetAccountWithdrawals -> {
                    getResponse {
                        blockFrostClient.getAccountWithdrawals(stakeAddress)
                    }
                }

                ResultEvent.GetSpecificAddress -> {
                    getResponse {
                        blockFrostClient.getSpecificAddress(address)
                    }
                }

                ResultEvent.GetSpecificAddressExtended -> {
                    getResponse {
                        blockFrostClient.getSpecificAddressExtended(address)
                    }
                }

                ResultEvent.GetAddressDetail -> {
                    getResponse {
                        blockFrostClient.getAddressDetail(address)
                    }
                }

                ResultEvent.GetAddressUtxos -> {
                    getResponse {
                        blockFrostClient.getAddressUtxos(address)
                    }
                }

                ResultEvent.GetAssets -> {
                    getResponse {
                        blockFrostClient.getAssets()
                    }
                }

                ResultEvent.GetSpecificAsset -> {
                    getResponse {
                        blockFrostClient.getSpecificAsset(asset)
                    }
                }

                ResultEvent.GetAssetHistory -> {
                    getResponse {
                        blockFrostClient.getAssetHistory(asset)
                    }
                }

                ResultEvent.GetAssetTxs -> {
                    getResponse {
                        blockFrostClient.getAssetTxs(asset)
                    }
                }

                ResultEvent.GetAssetTransactions -> {
                    getResponse {
                        blockFrostClient.getAssetTransactions(asset)
                    }
                }

                ResultEvent.GetAssetAddresses -> {
                    getResponse {
                        blockFrostClient.getAssetAddresses(asset)
                    }
                }

                ResultEvent.GetAssetPolicy -> {
                    getResponse {
                        blockFrostClient.getAssetPolicy(policyId)
                    }
                }

                ResultEvent.GetLatestBlock -> {
                    getResponse {
                        blockFrostClient.getLatestBlock()
                    }
                }

                ResultEvent.GetLatestBlockTxs -> {
                    getResponse {
                        blockFrostClient.getLatestBlockTxs()
                    }
                }

                ResultEvent.GetSpecificBlock -> {
                    getResponse {
                        blockFrostClient.getSpecificBlock(hastOrNumber)
                    }
                }

                ResultEvent.GetNextBlocks -> {
                    getResponse {
                        blockFrostClient.getNextBlocks(hastOrNumber)
                    }
                }

                ResultEvent.GetPreviousBlocks -> {
                    getResponse {
                        blockFrostClient.getPreviousBlocks(hastOrNumber)
                    }
                }

                ResultEvent.GetBlockInSlot -> {
                    getResponse {
                        blockFrostClient.getBlockInSlot(0)
                    }
                }

                ResultEvent.GetBlockInSlotInEpoch -> {
                    getResponse {
                        blockFrostClient.getBlockInSlotInEpoch(0, 0)
                    }
                }

                ResultEvent.GetBlockTransaction -> {
                    getResponse {
                        blockFrostClient.getBlockTransaction(hastOrNumber)
                    }
                }

                ResultEvent.GetAddressAffectedInSpecificBlock -> {
                    getResponse {
                        blockFrostClient.getAddressAffectedInSpecificBlock(hastOrNumber)
                    }
                }

                ResultEvent.GetLatestEpoch -> {
                    getResponse {
                        blockFrostClient.getLatestEpoch()
                    }
                }

                ResultEvent.GetLatestEpochProtocolParameters -> {
                    getResponse {
                        blockFrostClient.getLatestEpochProtocolParameters()
                    }
                }

                ResultEvent.GetSpecificEpoch -> {
                    getResponse {
                        blockFrostClient.getSpecificEpoch(epoch)
                    }
                }

                ResultEvent.GetListNextEpochs -> {
                    getResponse {
                        blockFrostClient.getListNextEpochs(epoch)
                    }
                }

                ResultEvent.GetListPreviousEpochs -> {
                    getResponse {
                        blockFrostClient.getListPreviousEpochs(epoch)
                    }
                }

                ResultEvent.GetStakeDistribution -> {
                    getResponse {
                        blockFrostClient.getStakeDistribution(epoch)
                    }
                }

                ResultEvent.GetStakeDistributionPool -> {
                    getResponse {
                        blockFrostClient.getStakeDistributionPool(epoch, poolId)
                    }
                }

                ResultEvent.GetBlockDistribution -> {
                    getResponse {
                        blockFrostClient.getBlockDistribution(epoch)
                    }
                }

                ResultEvent.GetBlockDistributionPool -> {
                    getResponse {
                        blockFrostClient.getBlockDistributionPool(epoch, poolId)
                    }
                }

                ResultEvent.GetProtocolParameters -> {
                    getResponse {
                        blockFrostClient.getProtocolParameters(epoch)
                    }
                }

                ResultEvent.GetBlockchainGenesis -> {
                    getResponse {
                        blockFrostClient.getBlockchainGenesis()
                    }
                }

                ResultEvent.GetDeriveAddress -> {
                    getResponse {
                        blockFrostClient.getDerivedAddress(
                            xpub,
                            0,
                            0,
                        )
                    }
                }

                ResultEvent.SubmitTransaction -> {
                    getResponse {
                        blockFrostClient.submitTransactionForExecutionUnitsEvaluation("")
                    }
                }

                ResultEvent.GetMempool -> {
                    getResponse {
                        blockFrostClient.getMempool()
                    }
                }

                ResultEvent.GetMempoolDetails -> {
                    blockFrostClient.getMempool().getOrNull()?.firstOrNull()?.let {
                        getResponse {
                            blockFrostClient.getMempoolDetails(it.txHash)
                        }
                    }
                }

                ResultEvent.GetMemPoolByAddress -> {
                    getResponse {
                        blockFrostClient.getMemPoolByAddress(address)
                    }
                }

                ResultEvent.GetTransactionMetadataLabels -> {
                    getResponse {
                        blockFrostClient.getTransactionMetadataLabels()
                    }
                }

                ResultEvent.GetTransactionMetadataContents -> {
                    getResponse {
                        blockFrostClient.getTransactionMetadataContents(cbor)
                    }
                }

                ResultEvent.GetTransactionMetadataContentCBOR -> {
                    getResponse {
                        blockFrostClient.getTransactionMetadataContentCBOR(cbor)
                    }
                }

                ResultEvent.GetNetworkInformation -> {
                    getResponse {
                        blockFrostClient.getNetworkInformation()
                    }
                }

                ResultEvent.QuerySummaryBlockchainEras -> {
                    getResponse {
                        blockFrostClient.querySummaryBlockchainEras()
                    }
                }

                ResultEvent.GetListStakePools -> {
                    getResponse {
                        blockFrostClient.getListStakePools()
                    }
                }

                ResultEvent.GetListStakePoolsExtended -> {
                    getResponse {
                        blockFrostClient.getListStakePoolsExtended()
                    }
                }

                ResultEvent.GetSpecificStakePool -> {
                    getResponse {
                        blockFrostClient.getSpecificStakePool(poolId)
                    }
                }

                ResultEvent.GetStakePoolHistory -> {
                    getResponse {
                        blockFrostClient.getStakePoolHistory(poolId)
                    }
                }

                ResultEvent.GetStakePoolMetadata -> {
                    getResponse {
                        blockFrostClient.getStakePoolMetadata(poolId)
                    }
                }

                ResultEvent.GetStakePoolRelays -> {
                    getResponse {
                        blockFrostClient.getStakePoolRelays(poolId)
                    }
                }

                ResultEvent.GetListStakePoolDelegators -> {
                    getResponse {
                        blockFrostClient.getListStakePoolDelegators(poolId)
                    }
                }

                ResultEvent.GetListStakePoolBlocks -> {
                    getResponse {
                        blockFrostClient.getListStakePoolBlocks(poolId)
                    }
                }

                ResultEvent.GetListStakePoolUpdates -> {
                    getResponse {
                        blockFrostClient.getListStakePoolUpdates(poolId)
                    }
                }

                ResultEvent.GetListRetiredStakePools -> {
                    getResponse {
                        blockFrostClient.getListRetiredStakePools()
                    }
                }

                ResultEvent.GetListRetiringStakePools -> {
                    getResponse {
                        blockFrostClient.getListRetiringStakePools()
                    }
                }

                ResultEvent.GetScript -> {
                    getResponse { blockFrostClient.getScript(scriptHash) }
                }

                ResultEvent.GetScriptCbor -> {
                    getResponse { blockFrostClient.getScriptCbor(scriptHash) }
                }

                ResultEvent.GetScriptDatum -> {
                    getResponse { blockFrostClient.getScriptDatum(datumHash) }
                }

                ResultEvent.GetScriptDatumCbor -> {
                    getResponse { blockFrostClient.getScriptDatumCbor(datumHash) }
                }

                ResultEvent.GetScriptJson -> {
                    getResponse { blockFrostClient.getScriptJson(scriptHash) }
                }

                ResultEvent.GetScriptRedeemers -> {
                    getResponse { blockFrostClient.getScriptRedeemers(scriptHash) }
                }

                ResultEvent.GetScripts -> {
                    getResponse { blockFrostClient.getScripts() }
                }

                ResultEvent.GetSpecificTransaction -> {
                    getResponse { blockFrostClient.getSpecificTransaction(hashTransaction) }
                }

                ResultEvent.GetTransactionDelegations -> {
                    getResponse {
                        blockFrostClient.getTransactionDelegations(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionMetadata -> {
                    getResponse {
                        blockFrostClient.getTransactionMetadata(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionMetadataCbor -> {
                    getResponse {
                        blockFrostClient.getTransactionMetadataCbor(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionMirs -> {
                    getResponse {
                        blockFrostClient.getTransactionMirs(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionPoolRetires -> {
                    getResponse {
                        blockFrostClient.getTransactionPoolRetires(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionPoolUpdates -> {
                    getResponse {
                        blockFrostClient.getTransactionPoolUpdates(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionRedeemers -> {
                    getResponse {
                        blockFrostClient.getTransactionRedeemers(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionStakes -> {
                    getResponse {
                        blockFrostClient.getTransactionStakes(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionUtxos -> {
                    getResponse {
                        blockFrostClient.getTransactionUtxos(hashTransaction)
                    }
                }

                ResultEvent.GetTransactionWithdrawals -> {
                    getResponse {
                        blockFrostClient.getTransactionWithdrawals(hashTransaction)
                    }
                }
            }
        }
    }

    fun onReceiveId(id: String) =
        onTriggerEvent(
            id2Event[id] ?: throw IllegalArgumentException("Unknown id: $id"),
        )

    private suspend fun getResponse(block: suspend () -> Result<*>) {
        state.value = state.value.copy(isLoading = true)
        val response = block.invoke()
        state.value =
            state.value.copy(
                isLoading = false,
                result = response.toResultString(),
            )
    }

    private fun <T> Result<T>.toResultString() = getOrNull()?.toString() ?: exceptionOrNull().toString()

    companion object {
        val id2Event =
            mapOf(
                "GetApiRoot" to ResultEvent.GetApiRoot,
                "GetHealth" to ResultEvent.GetHealth,
                "GetCurrentBackendTime" to ResultEvent.GetCurrentBackendTime,
                "GetMetrics" to ResultEvent.GetMetrics,
                "GetMetricEndpoints" to ResultEvent.GetMetricEndpoints,
                "GetAccount" to ResultEvent.GetAccount,
                "GetAccountAddressesAsset" to ResultEvent.GetAccountAddressesAsset,
                "GetAccountAddresses" to ResultEvent.GetAccountAddresses,
                "GetAccountAddressesTotal" to ResultEvent.GetAccountAddressesTotal,
                "GetAccountDelegations" to ResultEvent.GetAccountDelegations,
                "GetAccountHistory" to ResultEvent.GetAccountHistory,
                "GetAccountMirs" to ResultEvent.GetAccountMirs,
                "GetAccountRegistrations" to ResultEvent.GetAccountRegistrations,
                "GetAccountRewards" to ResultEvent.GetAccountRewards,
                "GetAccountWithdrawals" to ResultEvent.GetAccountWithdrawals,
                "GetSpecificAddress" to ResultEvent.GetSpecificAddress,
                "GetSpecificAddressExtended" to ResultEvent.GetSpecificAddressExtended,
                "GetAddressDetail" to ResultEvent.GetAddressDetail,
                "GetAddressUtxos" to ResultEvent.GetAddressUtxos,
                "GetAssets" to ResultEvent.GetAssets,
                "GetSpecificAsset" to ResultEvent.GetSpecificAsset,
                "GetAssetHistory" to ResultEvent.GetAssetHistory,
                "GetAssetTxs" to ResultEvent.GetAssetTxs,
                "GetAssetTransactions" to ResultEvent.GetAssetTransactions,
                "GetAssetAddresses" to ResultEvent.GetAssetAddresses,
                "GetAssetPolicy" to ResultEvent.GetAssetPolicy,
                "GetLatestBlock" to ResultEvent.GetLatestBlock,
                "GetLatestBlockTxs" to ResultEvent.GetLatestBlockTxs,
                "GetSpecificBlock" to ResultEvent.GetSpecificBlock,
                "GetNextBlocks" to ResultEvent.GetNextBlocks,
                "GetPreviousBlocks" to ResultEvent.GetPreviousBlocks,
                "GetBlockInSlot" to ResultEvent.GetBlockInSlot,
                "GetBlockInSlotInEpoch" to ResultEvent.GetBlockInSlotInEpoch,
                "GetBlockTransaction" to ResultEvent.GetBlockTransaction,
                "GetAddressAffectedInSpecificBlock" to ResultEvent.GetAddressAffectedInSpecificBlock,
                "GetLatestEpoch" to ResultEvent.GetLatestEpoch,
                "GetLatestEpochProtocolParameters" to ResultEvent.GetLatestEpochProtocolParameters,
                "GetSpecificEpoch" to ResultEvent.GetSpecificEpoch,
                "GetListNextEpochs" to ResultEvent.GetListNextEpochs,
                "GetListPreviousEpochs" to ResultEvent.GetListPreviousEpochs,
                "GetStakeDistribution" to ResultEvent.GetStakeDistribution,
                "GetStakeDistributionPool" to ResultEvent.GetStakeDistributionPool,
                "GetBlockDistribution" to ResultEvent.GetBlockDistribution,
                "GetBlockDistributionPool" to ResultEvent.GetBlockDistributionPool,
                "GetProtocolParameters" to ResultEvent.GetProtocolParameters,
                "GetBlockchainGenesis" to ResultEvent.GetBlockchainGenesis,
                "GetDeriveAddress" to ResultEvent.GetDeriveAddress,
                "SubmitTransaction" to ResultEvent.SubmitTransaction,
                "GetMempool" to ResultEvent.GetMempool,
                "GetMempoolDetails" to ResultEvent.GetMempoolDetails,
                "GetMemPoolByAddress" to ResultEvent.GetMemPoolByAddress,
                "GetTransactionMetadataLabels" to ResultEvent.GetTransactionMetadataLabels,
                "GetTransactionMetadataContents" to ResultEvent.GetTransactionMetadataContents,
                "GetTransactionMetadataContentCBOR" to ResultEvent.GetTransactionMetadataContentCBOR,
                "GetNetworkInformation" to ResultEvent.GetNetworkInformation,
                "QuerySummaryBlockchainEras" to ResultEvent.QuerySummaryBlockchainEras,
                "GetListStakePools" to ResultEvent.GetListStakePools,
                "GetListStakePoolsExtended" to ResultEvent.GetListStakePoolsExtended,
                "GetSpecificStakePool" to ResultEvent.GetSpecificStakePool,
                "GetStakePoolHistory" to ResultEvent.GetStakePoolHistory,
                "GetStakePoolMetadata" to ResultEvent.GetStakePoolMetadata,
                "GetStakePoolRelays" to ResultEvent.GetStakePoolRelays,
                "GetListStakePoolDelegators" to ResultEvent.GetListStakePoolDelegators,
                "GetListStakePoolBlocks" to ResultEvent.GetListStakePoolBlocks,
                "GetListStakePoolUpdates" to ResultEvent.GetListStakePoolUpdates,
                "GetListRetiredStakePools" to ResultEvent.GetListRetiredStakePools,
                "GetListRetiringStakePools" to ResultEvent.GetListRetiringStakePools,
                "GetScripts" to ResultEvent.GetScripts,
                "GetScript" to ResultEvent.GetScript,
                "GetScriptRedeemers" to ResultEvent.GetScriptRedeemers,
                "GetScriptJson" to ResultEvent.GetScriptJson,
                "GetScriptCbor" to ResultEvent.GetScriptCbor,
                "GetScriptDatum" to ResultEvent.GetScriptDatum,
                "GetScriptDatumCbor" to ResultEvent.GetScriptDatumCbor,
                "GetSpecificTransaction" to ResultEvent.GetSpecificTransaction,
                "GetTransactionUtxos" to ResultEvent.GetTransactionUtxos,
                "GetTransactionStakes" to ResultEvent.GetTransactionStakes,
                "GetTransactionDelegations" to ResultEvent.GetTransactionDelegations,
                "GetTransactionWithdrawals" to ResultEvent.GetTransactionWithdrawals,
                "GetTransactionMirs" to ResultEvent.GetTransactionMirs,
                "GetTransactionPoolUpdates" to ResultEvent.GetTransactionPoolUpdates,
                "GetTransactionPoolRetires" to ResultEvent.GetTransactionPoolRetires,
                "GetTransactionMetadata" to ResultEvent.GetTransactionMetadata,
                "GetTransactionMetadataCbor" to ResultEvent.GetTransactionMetadataCbor,
                "GetTransactionRedeemers" to ResultEvent.GetTransactionRedeemers,
            )
    }
}
