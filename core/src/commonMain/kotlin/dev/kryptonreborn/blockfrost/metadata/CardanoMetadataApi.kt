package dev.kryptonreborn.blockfrost.metadata

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContent
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContentCBOR
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataLabel
import io.ktor.client.HttpClient

internal class CardanoMetadataApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_GET_TRANSACTION_METADATA_LABELS = "/api/v0/metadata/txs/labels"
        const val PATH_GET_TRANSACTION_METADATA_CONTENTS = "/api/v0/metadata/txs/labels/:label"
        const val PATH_GET_TRANSACTION_METADATA_LABEL_CBOR =
            "/api/v0/metadata/txs/labels/:label/cbor"
    }

    suspend fun getTransactionMetadataLabels(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<TransactionMetadataLabel>>(
            PATH_GET_TRANSACTION_METADATA_LABELS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getTransactionMetadataContents(
        label: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<TransactionMetadataContent>>(
        PATH_GET_TRANSACTION_METADATA_CONTENTS.replace(":label", label),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getTransactionMetadataContentCBOR(
        label: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<TransactionMetadataContentCBOR>>(
        PATH_GET_TRANSACTION_METADATA_LABEL_CBOR.replace(":label", label),
        queryParams = queryParameters.toMap(),
    )
}
