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
                is ResultEvent.GetApiRoot -> {
                    state.value = state.value.copy(isLoading = true)
                    val response = BlockFrostKotlinSdk.getApiRoot()
                    state.value =
                        state.value.copy(
                            isLoading = false,
                            result =
                                response.fold(
                                    { error -> error.message },
                                    { result -> result.toString() },
                                ),
                        )
                }

                ResultEvent.GetCurrentBackendTime -> {
                    state.value = state.value.copy(isLoading = true)
                    val response = BlockFrostKotlinSdk.getCurrentBackendTime()
                    state.value =
                        state.value.copy(
                            isLoading = false,
                            result =
                                response.fold(
                                    { error -> error.message },
                                    { result -> result.toString() },
                                ),
                        )
                }

                ResultEvent.GetHealth -> {
                    state.value = state.value.copy(isLoading = true)
                    val response = BlockFrostKotlinSdk.getHealth()
                    state.value =
                        state.value.copy(
                            isLoading = false,
                            result =
                                response.fold(
                                    { error -> error.message },
                                    { result -> result.toString() },
                                ),
                        )
                }
            }
        }
    }

    fun onReceiveId(id: String) =
        onTriggerEvent(
            id2Event[id] ?: throw IllegalArgumentException("Unknown id: $id"),
        )

    companion object {
        val id2Event =
            mapOf(
                "GetApiRoot" to ResultEvent.GetApiRoot,
                "GetHealth" to ResultEvent.GetHealth,
                "GetCurrentBackendTime" to ResultEvent.GetCurrentBackendTime,
            )
    }
}
