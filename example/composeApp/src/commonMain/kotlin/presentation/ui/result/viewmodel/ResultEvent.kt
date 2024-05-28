package presentation.ui.result.viewmodel

sealed class ResultEvent(val id: String) {
    data object GetApiRoot : ResultEvent("GetApiRoot")

    data object GetHealth : ResultEvent("GetHealth")

    data object GetCurrentBackendTime : ResultEvent("GetCurrentBackendTime")
}
