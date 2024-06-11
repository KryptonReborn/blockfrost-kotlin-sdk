package dev.kryptonreborn.blockfrost.addresses

import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

class CardanoAddressApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_SPECIFIC_ADDRESSES = "/api/v0/addresses/:address"
    }

    suspend fun getSpecificAddress(address: String) =
        httpClient.fetchResource<SpecificAddress>(
            PATH_SPECIFIC_ADDRESSES.replace(":address", address),
        )
}
