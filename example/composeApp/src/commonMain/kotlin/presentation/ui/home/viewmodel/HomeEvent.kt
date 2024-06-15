package presentation.ui.home.viewmodel

sealed interface HomeEvent {
    data class ToggleCardanoApi(val key: String) : HomeEvent
}
