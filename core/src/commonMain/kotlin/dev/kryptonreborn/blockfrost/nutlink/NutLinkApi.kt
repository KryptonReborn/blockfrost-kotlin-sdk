package dev.kryptonreborn.blockfrost.nutlink

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.nutlink.model.NutLinkAddressMetadata
import dev.kryptonreborn.blockfrost.nutlink.model.OracleTicker
import dev.kryptonreborn.blockfrost.nutlink.model.TickerRecord
import io.ktor.client.HttpClient

internal class NutLinkApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_GET_NUTLINK = "/api/v0/nutlink/:address"
        const val PATH_GET_NUTLINK_TICKERS = "/api/v0/nutlink/:address/tickers"
        const val PATH_GET_NUTLINK_SPECIFIC_TICKER_FOR_ADDRESS =
            "/api/v0/nutlink/:address/tickers/:ticker"
        const val PATH_GET_NUTLINK_SPECIFIC_TICKER = "/api/v0/nutlink/tickers/:ticker"
    }

    suspend fun getNutLink(address: String) =
        httpClient.fetchResource<NutLinkAddressMetadata>(
            PATH_GET_NUTLINK.replace(":address", address),
        )

    suspend fun getNutLinkTickers(
        address: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<OracleTicker>>(
        PATH_GET_NUTLINK_TICKERS.replace(":address", address),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getNutLinkSpecificTickerForAddress(
        address: String,
        ticker: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<TickerRecord>>(
        PATH_GET_NUTLINK_SPECIFIC_TICKER_FOR_ADDRESS.replace(":address", address)
            .replace(":ticker", ticker),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getNutLinkSpecificTicker(
        ticker: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<TickerRecord>>(
        PATH_GET_NUTLINK_SPECIFIC_TICKER.replace(":ticker", ticker),
        queryParams = queryParameters.toMap(),
    )
}
