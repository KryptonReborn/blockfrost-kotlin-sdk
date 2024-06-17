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
            )
    }
}
