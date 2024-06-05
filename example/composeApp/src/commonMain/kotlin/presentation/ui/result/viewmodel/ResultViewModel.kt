package presentation.ui.result.viewmodel

import BlockFrostClient
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.BlockfrostLogLevel
import dev.kryptonreborn.blockfrost.NetworkType
import kotlinx.coroutines.launch

class ResultViewModel : ViewModel() {
    private val stakeAddress = "stake1u9ylzsgxaa6xctf4juup682ar3juj85n8tx3hthnljg47zctvm3rc"
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
                is ResultEvent.GetApiRoot ->
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
            )
    }
}
