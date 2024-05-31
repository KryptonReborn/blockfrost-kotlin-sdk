package presentation.ui.result.viewmodel

import BlockFrostKotlinSdk
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ResultViewModel : ViewModel() {
    val state: MutableState<ResultState> = mutableStateOf(ResultState())

    fun onTriggerEvent(event: ResultEvent) {
        viewModelScope.launch {
            when (event) {
                is ResultEvent.GetApiRoot ->
                    getResponse {
                        BlockFrostKotlinSdk.getApiRoot()
                    }

                ResultEvent.GetCurrentBackendTime ->
                    getResponse {
                        BlockFrostKotlinSdk.getCurrentBackendTime()
                    }

                ResultEvent.GetHealth ->
                    getResponse {
                        BlockFrostKotlinSdk.getHealth()
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
                    response.getOrNull()?.toString() ?: response.exceptionOrNull()?.message
                        ?: "Unknown error",
            )
    }

    companion object {
        val id2Event =
            mapOf(
                "GetApiRoot" to ResultEvent.GetApiRoot,
                "GetHealth" to ResultEvent.GetHealth,
                "GetCurrentBackendTime" to ResultEvent.GetCurrentBackendTime,
            )
    }
}
