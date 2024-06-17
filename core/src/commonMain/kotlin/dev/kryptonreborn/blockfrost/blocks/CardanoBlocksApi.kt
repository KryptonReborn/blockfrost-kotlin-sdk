package dev.kryptonreborn.blockfrost.blocks

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.blocks.model.BlockAddress
import dev.kryptonreborn.blockfrost.blocks.model.BlockContent
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

internal class CardanoBlocksApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_LATEST_BLOCK = "/api/v0/blocks/latest"
        const val PATH_LATEST_BLOCK_TXS = "/api/v0/blocks/latest/txs"
        const val PATH_SPECIFIC_BLOCK = "/api/v0/blocks/:hash_or_number"
        const val PATH_NEXT_BLOCKS = "/api/v0/blocks/:hash_or_number/next"
        const val PATH_PREVIOUS_BLOCKS = "/api/v0/blocks/:hash_or_number/previous"
        const val PATH_BLOCK_IN_SLOT = "/api/v0/blocks/slot/:slot_number"
        const val PATH_BLOCK_IN_SLOT_IN_EPOCH =
            "/api/v0/blocks/epoch/:epoch_number/slot/:slot_number"
        const val PATH_BLOCK_TRANSACTION = "/api/v0/blocks/:hash_or_number/txs"
        const val PATH_ADDRESS_AFFECTED = "/api/v0/blocks/:hash_or_number/addresses"
    }

    suspend fun getLatestBlock() = httpClient.fetchResource<BlockContent>(PATH_LATEST_BLOCK)

    suspend fun getLatestBlockTxs(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<String>>(
            PATH_LATEST_BLOCK_TXS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getSpecificBlock(hashOrNumber: String) =
        httpClient.fetchResource<BlockContent>(
            PATH_SPECIFIC_BLOCK.replace(":hash_or_number", hashOrNumber),
        )

    suspend fun getNextBlocks(
        hashOrNumber: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<BlockContent>>(
        PATH_NEXT_BLOCKS.replace(":hash_or_number", hashOrNumber),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getPreviousBlocks(
        hashOrNumber: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<BlockContent>>(
        PATH_PREVIOUS_BLOCKS.replace(":hash_or_number", hashOrNumber),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getBlockInSlot(slotNumber: Int) =
        httpClient.fetchResource<BlockContent>(
            PATH_BLOCK_IN_SLOT.replace(":slot_number", slotNumber.toString()),
        )

    suspend fun getBlockInSlotInEpoch(
        epochNumber: Int,
        slotNumber: Int,
    ) = httpClient.fetchResource<BlockContent>(
        PATH_BLOCK_IN_SLOT_IN_EPOCH
            .replace(":epoch_number", epochNumber.toString())
            .replace(":slot_number", slotNumber.toString()),
    )

    suspend fun getBlockTransaction(
        hashOrNumber: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<String>>(
        PATH_BLOCK_TRANSACTION.replace(":hash_or_number", hashOrNumber),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getAddressAffectedInSpecificBlock(hashOrNumber: String) =
        httpClient.fetchResource<List<BlockAddress>>(
            PATH_ADDRESS_AFFECTED.replace(
                ":hash_or_number",
                hashOrNumber,
            ),
        )
}
