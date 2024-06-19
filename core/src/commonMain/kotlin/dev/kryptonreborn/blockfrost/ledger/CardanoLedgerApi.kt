package dev.kryptonreborn.blockfrost.ledger

import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.ledger.model.BlockchainGenesis
import io.ktor.client.HttpClient

internal class CardanoLedgerApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_BLOCKCHAIN_GENESIS = "/api/v0/genesis"
    }

    suspend fun getBlockchainGenesis() =
        httpClient.fetchResource<BlockchainGenesis>(
            PATH_BLOCKCHAIN_GENESIS,
        )
}
