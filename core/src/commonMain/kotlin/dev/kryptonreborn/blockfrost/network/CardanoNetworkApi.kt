package dev.kryptonreborn.blockfrost.network

import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.network.model.EraSummary
import dev.kryptonreborn.blockfrost.network.model.NetworkInformation
import io.ktor.client.HttpClient

internal class CardanoNetworkApi(private val httpClient: HttpClient) {
    companion object {
        const val GET_NETWORK_INFORMATION = "/api/v0/network"
        const val QUERY_SUMMARY_BLOCKCHAIN_ERAS = "/api/v0/network/eras"
    }

    suspend fun getNetworkInformation() = httpClient.fetchResource<NetworkInformation>(GET_NETWORK_INFORMATION)

    suspend fun querySummaryBlockchainEras() = httpClient.fetchResource<List<EraSummary>>(QUERY_SUMMARY_BLOCKCHAIN_ERAS)
}
