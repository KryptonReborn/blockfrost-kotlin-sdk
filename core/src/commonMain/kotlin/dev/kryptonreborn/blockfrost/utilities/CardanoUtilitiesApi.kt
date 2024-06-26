package dev.kryptonreborn.blockfrost.utilities

import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.JsonObject

internal class CardanoUtilitiesApi(private val client: HttpClient) {
    companion object {
        const val PATH_DERIVE_ADDRESS = "/api/v0/utils/addresses/xpub/:xpub/:role/:index"
        const val PATH_SUBMIT_TRANSACTION = "/api/v0/utils/txs/evaluate"
    }

    suspend fun getDerivedAddress(
        xpub: String,
        role: Int,
        index: Int,
    ) = client.fetchResource<DerivedAddress>(
        PATH_DERIVE_ADDRESS.replace(":xpub", xpub)
            .replace(":role", role.toString())
            .replace(":index", index.toString()),
    )

    suspend fun submitTransactionForExecutionUnitsEvaluation(transaction: String) =
        client.fetchResource<JsonObject>(
            path = PATH_SUBMIT_TRANSACTION,
            method = HttpMethod.Post,
            requestBody = transaction,
            contentType = ContentType.Application.Cbor,
        )
}
