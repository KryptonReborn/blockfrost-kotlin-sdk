package dev.kryptonreborn.blockfrost.epochs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Epoch
 *
 *  @property epoch Epoch number
 *  @property startTime Unix time of the start of the epoch
 *  @property endTime Unix time of the end of the epoch
 *  @property firstBlockTime Unix time of the first block of the epoch
 *  @property lastBlockTime Unix time of the last block of the epoch
 *  @property blockCount Number of blocks within the epoch
 *  @property txCount Number of transactions within the epoch
 *  @property output Sum of all the transactions within the epoch in Lovelaces
 *  @property fees Sum of all the fees within the epoch in Lovelaces
 *  @property activeStake Sum of all the active stakes within the epoch in Lovelaces
 */
@Serializable
data class Epoch(
    val epoch: Int,
    @SerialName("start_time") val startTime: Int,
    @SerialName("end_time") val endTime: Int,
    @SerialName("first_block_time") val firstBlockTime: Int,
    @SerialName("last_block_time") val lastBlockTime: Int,
    @SerialName("block_count") val blockCount: Int,
    @SerialName("tx_count") val txCount: Int,
    val output: String,
    val fees: String,
    @SerialName("active_stake") val activeStake: String?,
)
