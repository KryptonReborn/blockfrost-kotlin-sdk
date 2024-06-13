package presentation.ui.result.viewmodel

sealed class ResultEvent(val id: String) {
    data object GetApiRoot : ResultEvent("GetApiRoot")

    data object GetHealth : ResultEvent("GetHealth")

    data object GetCurrentBackendTime : ResultEvent("GetCurrentBackendTime")

    data object GetMetrics : ResultEvent("GetMetrics")

    data object GetMetricEndpoints : ResultEvent("GetMetricEndpoints")

    data object GetAccount : ResultEvent("GetAccount")

    data object GetAccountAddressesAsset : ResultEvent("GetAccountAddressesAsset")

    data object GetAccountAddresses : ResultEvent("GetAccountAddresses")

    data object GetAccountAddressesTotal : ResultEvent("GetAccountAddressesTotal")

    data object GetAccountDelegations : ResultEvent("GetAccountDelegations")

    data object GetAccountHistory : ResultEvent("GetAccountHistory")

    data object GetAccountMirs : ResultEvent("GetAccountMirs")

    data object GetAccountRegistrations : ResultEvent("GetAccountRegistrations")

    data object GetAccountRewards : ResultEvent("GetAccountRewards")

    data object GetAccountWithdrawals : ResultEvent("GetAccountWithdrawals")

    data object GetSpecificAddress : ResultEvent("GetSpecificAddress")

    data object GetSpecificAddressExtended : ResultEvent("GetSpecificAddressExtended")

    data object GetAddressDetail : ResultEvent("GetAddressDetail")

    data object GetAddressUtxos : ResultEvent("GetAddressUtxos")

    data object GetAssets : ResultEvent("GetAssets")

    data object GetSpecificAsset : ResultEvent("GetSpecificAsset")

    data object GetAssetHistory : ResultEvent("GetAssetHistory")
}
