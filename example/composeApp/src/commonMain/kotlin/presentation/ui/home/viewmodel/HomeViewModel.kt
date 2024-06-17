package presentation.ui.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val state: MutableState<HomeState> = mutableStateOf(HomeState())

    fun onTriggerEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ToggleCardanoApi -> {
                val items = state.value.items.toMutableMap()
                val item = items[event.key] ?: return
                items[event.key] = item.copy(show = !item.show)
                state.value = state.value.copy(items = items)
            }
        }
    }
}
