package presentation.ui.home.viewmodel

sealed interface HomeEvent {
    data object GetApiRoot : HomeEvent
}
