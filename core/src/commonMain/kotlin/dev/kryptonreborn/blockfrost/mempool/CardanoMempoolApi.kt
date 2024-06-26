package dev.kryptonreborn.blockfrost.mempool

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransaction
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransactionDetails
import io.ktor.client.HttpClient

internal class CardanoMempoolApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_GET_MEMPOOL = "/api/v0/mempool"
        const val PATH_GET_MEMPOOL_DETAIL = "/api/v0/mempool/:hash"
        const val PATH_GET_MEMPOOL_BY_ADDRESS = "/api/v0/mempool/addresses/:address"
    }

    suspend fun getMempool(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<MempoolTransaction>>(
            PATH_GET_MEMPOOL,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getMempoolDetails(hash: String) =
        httpClient.fetchResource<MempoolTransactionDetails>(
            PATH_GET_MEMPOOL_DETAIL.replace(":hash", hash),
        )

    suspend fun getMempoolByAddress(
        address: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<MempoolTransaction>>(
        PATH_GET_MEMPOOL_BY_ADDRESS.replace(":address", address),
        queryParams = queryParameters.toMap(),
    )
}
