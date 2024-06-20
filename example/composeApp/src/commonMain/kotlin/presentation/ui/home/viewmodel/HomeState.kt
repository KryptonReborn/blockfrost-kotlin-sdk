package presentation.ui.home.viewmodel

data class HomeState(
    val title: String = "Home screen",
    val items: Map<String, HomeCardanoApi> =
        mapOf(
            "Health Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetApiRoot",
                            "GetHealth",
                            "GetCurrentBackendTime",
                        ),
                ),
            "Metrics Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetMetrics",
                            "GetMetricEndpoints",
                        ),
                ),
            "Cardano Account Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetAccount",
                            "GetAccountAddressesAsset",
                            "GetAccountAddresses",
                            "GetAccountAddressesTotal",
                            "GetAccountDelegations",
                            "GetAccountHistory",
                            "GetAccountMirs",
                            "GetAccountRegistrations",
                            "GetAccountRewards",
                            "GetAccountWithdrawals",
                        ),
                ),
            "Cardano Address Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetSpecificAddress",
                            "GetSpecificAddressExtended",
                            "GetAddressDetail",
                            "GetAddressUtxos",
                            "GetLatestBlockTxs",
                        ),
                ),
            "Cardano Asset Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetAssets",
                            "GetSpecificAsset",
                            "GetAssetHistory",
                            "GetAssetTxs",
                            "GetAssetTransactions",
                            "GetAssetAddresses",
                            "GetAssetPolicy",
                        ),
                ),
            "Cardano Block Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetLatestBlock",
                            "GetLatestBlockTxs",
                            "GetSpecificBlock",
                            "GetNextBlocks",
                            "GetPreviousBlocks",
                            "GetBlockInSlot",
                            "GetBlockInSlotInEpoch",
                            "GetBlockTransaction",
                            "GetAddressAffectedInSpecificBlock",
                        ),
                ),
            "Cardano Epoch Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetLatestEpoch",
                            "GetLatestEpochProtocolParameters",
                            "GetSpecificEpoch",
                            "GetListNextEpochs",
                            "GetListPreviousEpochs",
                            "GetStakeDistribution",
                            "GetStakeDistributionPool",
                            "GetBlockDistribution",
                            "GetBlockDistributionPool",
                            "GetProtocolParameters",
                        ),
                ),
            "Cardano Ledger Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetBlockchainGenesis",
                        ),
                ),
            "Cardano Utilities Api" to
                HomeCardanoApi(
                    show = false,
                    items =
                        listOf(
                            "GetDeriveAddress",
                            "SubmitTransaction",
                            "SubmitTransactionWithUtxos",
                        ),
                ),
        ),
)

data class HomeCardanoApi(
    val show: Boolean = false,
    val items: List<String>,
)
