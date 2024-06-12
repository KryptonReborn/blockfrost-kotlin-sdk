package dev.kryptonreborn.blockfrost.addresses

import dev.kryptonreborn.blockfrost.addresses.model.AddressDetail
import dev.kryptonreborn.blockfrost.addresses.model.AddressTransaction
import dev.kryptonreborn.blockfrost.addresses.model.AddressUTXO
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

internal class CardanoAddressApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_SPECIFIC_ADDRESSES = "/api/v0/addresses/:address"
        const val PATH_SPECIFIC_ADDRESSES_EXTENDED = "/api/v0/addresses/:address/extended"
        const val PATH_ADDRESS_DETAIL = "/api/v0/addresses/:address/total"
        const val PATH_ADDRESS_UTXOS = "/api/v0/addresses/:address/utxos"
        const val PATH_ADDRESS_UTXOS_ASSETS = "/api/v0/addresses/:address/assets"
        const val PATH_ADDRESS_TRANSACTIONS = "/api/v0/addresses/:address/transactions"
        const val PATH_ADDRESS_TXS = "/api/v0/addresses/:address/txs"
    }

    suspend fun getSpecificAddress(address: String) =
        httpClient.fetchResource<SpecificAddress>(
            PATH_SPECIFIC_ADDRESSES.replace(":address", address),
        )

    suspend fun getSpecificAddressExtended(address: String) =
        httpClient.fetchResource<SpecificAddress>(
            PATH_SPECIFIC_ADDRESSES_EXTENDED.replace(":address", address),
        )

    suspend fun getAddressDetail(address: String) =
        httpClient.fetchResource<AddressDetail>(
            PATH_ADDRESS_DETAIL.replace(":address", address),
        )

    suspend fun getAddressUtxos(
        address: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<AddressUTXO>>(
        PATH_ADDRESS_UTXOS.replace(":address", address),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAddressUtxosAssets(
        address: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<AddressUTXO>>(
        PATH_ADDRESS_UTXOS_ASSETS.replace(":address", address),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAddressTransactions(
        address: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<AddressTransaction>>(
        PATH_ADDRESS_TRANSACTIONS.replace(":address", address),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAddressTxs(
        address: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<String>>(
        PATH_ADDRESS_TXS.replace(":address", address),
        queryParams = queryParameters.toMap(),
    )
}
