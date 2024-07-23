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

    data object GetAssetTxs : ResultEvent("GetAssetTxs")

    data object GetAssetTransactions : ResultEvent("GetAssetTransactions")

    data object GetAssetAddresses : ResultEvent("GetAssetAddresses")

    data object GetAssetPolicy : ResultEvent("GetAssetPolicy")

    data object GetLatestBlock : ResultEvent("GetLatestBlock")

    data object GetLatestBlockTxs : ResultEvent("GetLatestBlockTxs")

    data object GetSpecificBlock : ResultEvent("GetSpecificBlock")

    data object GetNextBlocks : ResultEvent("GetNextBlocks")

    data object GetPreviousBlocks : ResultEvent("GetPreviousBlocks")

    data object GetBlockInSlot : ResultEvent("GetBlockInSlot")

    data object GetBlockInSlotInEpoch : ResultEvent("GetBlockInSlotInEpoch")

    data object GetBlockTransaction : ResultEvent("GetBlockTransaction")

    data object GetAddressAffectedInSpecificBlock : ResultEvent("GetAddressAffectedInSpecificBlock")

    data object GetLatestEpoch : ResultEvent("GetLatestEpoch")

    data object GetLatestEpochProtocolParameters : ResultEvent("GetLatestEpochProtocolParameters")

    data object GetSpecificEpoch : ResultEvent("GetSpecificEpoch")

    data object GetListNextEpochs : ResultEvent("GetListNextEpochs")

    data object GetListPreviousEpochs : ResultEvent("GetListPreviousEpochs")

    data object GetStakeDistribution : ResultEvent("GetStakeDistribution")

    data object GetStakeDistributionPool : ResultEvent("GetStakeDistributionPool")

    data object GetBlockDistribution : ResultEvent("GetBlockDistribution")

    data object GetBlockDistributionPool : ResultEvent("GetBlockDistributionPool")

    data object GetProtocolParameters : ResultEvent("GetProtocolParameters")

    data object GetBlockchainGenesis : ResultEvent("GetBlockchainGenesis")

    data object GetDeriveAddress : ResultEvent("GetDeriveAddress")

    data object SubmitTransaction : ResultEvent("SubmitTransaction")

    data object GetMempool : ResultEvent("GetMempool")

    data object GetMempoolDetails : ResultEvent("GetMempoolDetails")

    data object GetMemPoolByAddress : ResultEvent("GetMemPoolByAddress")

    data object GetTransactionMetadataLabels : ResultEvent("GetTransactionMetadataLabels")

    data object GetTransactionMetadataContents : ResultEvent("GetTransactionMetadataContents")

    data object GetTransactionMetadataContentCBOR : ResultEvent("GetTransactionMetadataContentCBOR")

    data object GetNetworkInformation : ResultEvent("GetNetworkInformation")

    data object QuerySummaryBlockchainEras : ResultEvent("QuerySummaryBlockchainEras")

    data object GetListStakePools : ResultEvent("GetListStakePools")

    data object GetListStakePoolsExtended : ResultEvent("GetListStakePoolsExtended")

    data object GetListRetiredStakePools : ResultEvent("GetListRetiredStakePools")

    data object GetListRetiringStakePools : ResultEvent("GetListRetiringStakePools")

    data object GetSpecificStakePool : ResultEvent("GetSpecificStakePool")

    data object GetStakePoolHistory : ResultEvent("GetStakePoolHistory")

    data object GetStakePoolMetadata : ResultEvent("GetStakePoolMetadata")

    data object GetStakePoolRelays : ResultEvent("GetStakePoolRelays")

    data object GetListStakePoolDelegators : ResultEvent("GetStakePoolDelegators")

    data object GetListStakePoolBlocks : ResultEvent("GetStakePoolBlocks")

    data object GetListStakePoolUpdates : ResultEvent("GetStakePoolUpdates")

    data object GetScripts : ResultEvent("GetScripts")

    data object GetScript : ResultEvent("GetScript")

    data object GetScriptJson : ResultEvent("GetScriptJson")

    data object GetScriptCbor : ResultEvent("GetScriptCbor")

    data object GetScriptRedeemers : ResultEvent("GetScriptRedeemers")

    data object GetScriptDatum : ResultEvent("GetScriptDatum")

    data object GetScriptDatumCbor : ResultEvent("GetScriptDatumCbor")

    data object GetSpecificTransaction : ResultEvent("GetSpecificTransaction")

    data object GetTransactionUtxos : ResultEvent("GetTransactionUtxos")

    data object GetTransactionStakes : ResultEvent("GetTransactionStakes")

    data object GetTransactionDelegations : ResultEvent("GetTransactionDelegations")

    data object GetTransactionWithdrawals : ResultEvent("GetTransactionWithdrawals")

    data object GetTransactionMirs : ResultEvent("GetTransactionMirs")

    data object GetTransactionPoolUpdates : ResultEvent("GetTransactionPoolUpdates")

    data object GetTransactionPoolRetires : ResultEvent("GetTransactionPoolRetires")

    data object GetTransactionMetadata : ResultEvent("GetTransactionMetadata")

    data object GetTransactionMetadataCbor : ResultEvent("GetTransactionMetadataCbor")

    data object GetTransactionRedeemers : ResultEvent("GetTransactionRedeemers")

    data object GetNutLink : ResultEvent("GetNutLink")

    data object GetNutLinkTickers : ResultEvent("GetNutLinkTickers")

    data object GetNutLinkSpecificTickerForAddress : ResultEvent("GetNutLinkSpecificTickerForAddress")

    data object GetNutLinkSpecificTicker : ResultEvent("GetNutLinkSpecificTicker")
}
