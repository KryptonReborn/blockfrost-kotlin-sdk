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
                    val resutl =
                        blockFrostClient.getAccount(stakeAddress).toResultString()
                            .toString() + "\n" +
                            blockFrostClient.getAccountRewards(stakeAddress)
                                .toResultString() + "\n" +
                            blockFrostClient.getAccountHistory(stakeAddress).toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountDelegations(stakeAddress)
                                .toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountRegistrations(stakeAddress)
                                .toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountWithdrawals(stakeAddress)
                                .toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountMirs(stakeAddress).toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountAddresses(stakeAddress).toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountAddressesAssets(stakeAddress)
                                .toResultString()
                                .toString() + "\n" +
                            blockFrostClient.getAccountAddressesTotal(stakeAddress)
                                .toResultString()
                                .toString()
                    state.value = state.value.copy(result = resutl)
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
                result =
                    response.toResultString()?.toString() ?: response.exceptionOrNull()?.message
                        ?: "Unknown error",
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
            )
    }
}
