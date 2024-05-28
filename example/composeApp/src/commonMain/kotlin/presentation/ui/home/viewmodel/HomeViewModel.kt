package presentation.ui.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val state: MutableState<HomeState> = mutableStateOf(HomeState())

    fun onTriggerEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetApiRoot -> {
            }
        }
    }
}
