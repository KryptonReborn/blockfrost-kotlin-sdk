package dev.kryptonreborn.blockfrost.assets

import dev.kryptonreborn.blockfrost.assets.model.Asset
import dev.kryptonreborn.blockfrost.assets.model.AssetAddress
import dev.kryptonreborn.blockfrost.assets.model.AssetHistory
import dev.kryptonreborn.blockfrost.assets.model.AssetTransaction
import dev.kryptonreborn.blockfrost.assets.model.SpecificAsset
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

internal class CardanoAssetsApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_ASSETS = "/api/v0/assets"
        const val PATH_SPECIFIC_ASSET = "/api/v0/assets/:asset"
        const val PATH_ASSET_HISTORY = "/api/v0/assets/:asset/history"
        const val PATH_ASSET_TXS = "/api/v0/assets/:asset/txs"
        const val PATH_ASSET_TRANSACTION = "/api/v0/assets/:asset/transactions"
        const val PATH_ASSET_ADDRESSES = "/api/v0/assets/:asset/addresses"
        const val PATH_ASSET_POLICY = "/api/v0/assets/policy/:policy_id"
    }

    suspend fun getAssets(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<Asset>>(
            PATH_ASSETS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getSpecificAsset(asset: String) =
        httpClient.fetchResource<SpecificAsset>(
            PATH_SPECIFIC_ASSET.replace(":asset", asset),
        )

    suspend fun getAssetHistory(
        asset: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<AssetHistory>>(
        PATH_ASSET_HISTORY.replace(":asset", asset),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAssetTxs(
        asset: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<String>>(
        PATH_ASSET_TXS.replace(":asset", asset),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAssetTransactions(
        asset: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<AssetTransaction>>(
        PATH_ASSET_TRANSACTION.replace(":asset", asset),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAssetAddresses(
        asset: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<AssetAddress>>(
        PATH_ASSET_ADDRESSES.replace(":asset", asset),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAssetPolicy(policyId: String) =
        httpClient.fetchResource<List<Asset>>(
            PATH_ASSET_POLICY.replace(":policy_id", policyId),
        )
}
