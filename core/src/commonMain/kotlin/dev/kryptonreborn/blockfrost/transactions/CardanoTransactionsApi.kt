package dev.kryptonreborn.blockfrost.transactions

import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.transactions.model.DelegationCertificate
import dev.kryptonreborn.blockfrost.transactions.model.SpecificTransaction
import dev.kryptonreborn.blockfrost.transactions.model.StakeAddressCertificate
import dev.kryptonreborn.blockfrost.transactions.model.StakePoolCertificate
import dev.kryptonreborn.blockfrost.transactions.model.StakePoolRetirementCertificate
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMIR
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadata
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadataCbor
import dev.kryptonreborn.blockfrost.transactions.model.TransactionRedeemer
import dev.kryptonreborn.blockfrost.transactions.model.TransactionUtxos
import dev.kryptonreborn.blockfrost.transactions.model.TransactionWithdrawal
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.JsonObject

internal class CardanoTransactionsApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_GET_SPECIFIC_TRANSACTION = "/api/v0/txs/:hash"
        const val PATH_GET_TRANSACTION_UTXOS = "/api/v0/txs/:hash/utxos"
        const val PATH_GET_TRANSACTION_STAKES = "/api/v0/txs/:hash/stakes"
        const val PATH_GET_TRANSACTION_DELEGATIONS = "/api/v0/txs/:hash/delegations"
        const val PATH_GET_TRANSACTION_WITHDRAWS = "/api/v0/txs/:hash/withdrawals"
        const val PATH_GET_TRANSACTION_MIRS = "/api/v0/txs/:hash/mirs"
        const val PATH_GET_TRANSACTION_POOL_UPDATES = "/api/v0/txs/:hash/pool_updates"
        const val PATH_GET_TRANSACTION_POOL_RETIRES = "/api/v0/txs/:hash/pool_retires"
        const val PATH_GET_TRANSACTION_METADATA = "/api/v0/txs/:hash/metadata"
        const val PATH_GET_TRANSACTION_METADATA_CBOR = "/api/v0/txs/:hash/metadata/cbor"
        const val PATH_GET_TRANSACTION_REDEEMERS = "/api/v0/txs/:hash/redeemers"
        const val PATH_SUBMIT_TRANSACTION = "/api/v0/tx/submit"
    }

    suspend fun getSpecificTransaction(hash: String) =
        httpClient.fetchResource<SpecificTransaction>(
            PATH_GET_SPECIFIC_TRANSACTION.replace(":hash", hash),
        )

    suspend fun getTransactionUtxos(hash: String) =
        httpClient.fetchResource<TransactionUtxos>(
            PATH_GET_TRANSACTION_UTXOS.replace(":hash", hash),
        )

    suspend fun getTransactionStakes(hash: String) =
        httpClient.fetchResource<List<StakeAddressCertificate>>(
            PATH_GET_TRANSACTION_STAKES.replace(":hash", hash),
        )

    suspend fun getTransactionDelegations(hash: String) =
        httpClient.fetchResource<List<DelegationCertificate>>(
            PATH_GET_TRANSACTION_DELEGATIONS.replace(":hash", hash),
        )

    suspend fun getTransactionWithdrawals(hash: String) =
        httpClient.fetchResource<List<TransactionWithdrawal>>(
            PATH_GET_TRANSACTION_WITHDRAWS.replace(":hash", hash),
        )

    suspend fun getTransactionMirs(hash: String) =
        httpClient.fetchResource<List<TransactionMIR>>(
            PATH_GET_TRANSACTION_MIRS.replace(":hash", hash),
        )

    suspend fun getTransactionPoolUpdates(hash: String) =
        httpClient.fetchResource<List<StakePoolCertificate>>(
            PATH_GET_TRANSACTION_POOL_UPDATES.replace(":hash", hash),
        )

    suspend fun getTransactionPoolRetires(hash: String) =
        httpClient.fetchResource<List<StakePoolRetirementCertificate>>(
            PATH_GET_TRANSACTION_POOL_RETIRES.replace(":hash", hash),
        )

    suspend fun getTransactionMetadata(hash: String) =
        httpClient.fetchResource<List<TransactionMetadata>>(
            PATH_GET_TRANSACTION_METADATA.replace(":hash", hash),
        )

    suspend fun getTransactionMetadataCbor(hash: String) =
        httpClient.fetchResource<List<TransactionMetadataCbor>>(
            PATH_GET_TRANSACTION_METADATA_CBOR.replace(":hash", hash),
        )

    suspend fun getTransactionRedeemers(hash: String) =
        httpClient.fetchResource<List<TransactionRedeemer>>(
            PATH_GET_TRANSACTION_REDEEMERS.replace(":hash", hash),
        )

    suspend fun submitTransaction(transaction: String) =
        httpClient.fetchResource<JsonObject>(
            PATH_SUBMIT_TRANSACTION,
            method = HttpMethod.Post,
            requestBody = transaction,
            contentType = ContentType.Application.Cbor,
        )
}
