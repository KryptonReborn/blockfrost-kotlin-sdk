package dev.kryptonreborn.blockfrost.blocks

import dev.kryptonreborn.blockfrost.blocks.model.Block
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
    }

    suspend fun getLatestBlock() = httpClient.fetchResource<Block>(PATH_LATEST_BLOCK)

    suspend fun getLatestBlockTxs() = httpClient.fetchResource<List<String>>(PATH_LATEST_BLOCK_TXS)

    suspend fun getSpecificBlock(hashOrNumber: String) =
        httpClient.fetchResource<Block>(
            PATH_SPECIFIC_BLOCK.replace(":hash_or_number", hashOrNumber),
        )

    suspend fun getNextBlocks(hashOrNumber: String) =
        httpClient.fetchResource<List<Block>>(
            PATH_NEXT_BLOCKS.replace(":hash_or_number", hashOrNumber),
        )

    suspend fun getPreviousBlocks(hashOrNumber: String) =
        httpClient.fetchResource<List<Block>>(
            PATH_PREVIOUS_BLOCKS.replace(":hash_or_number", hashOrNumber),
        )

    suspend fun getBlockInSlot(slotNumber: Int) =
        httpClient.fetchResource<Block>(
            PATH_BLOCK_IN_SLOT.replace(":slot_number", slotNumber.toString()),
        )

    suspend fun getBlockInSlotInEpoch(epochNumber: Int, slotNumber: Int) =
        httpClient.fetchResource<Block>(
            PATH_BLOCK_IN_SLOT_IN_EPOCH
                .replace(":epoch_number", epochNumber.toString())
                .replace(":slot_number", slotNumber.toString()),
        )

}
