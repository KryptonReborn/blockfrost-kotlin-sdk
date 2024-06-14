package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @property txHash Hash of the transaction
 * @property txIndex Transaction index within the block
 * @property blockHeight Block height
 */
@Serializable
data class AssetTransaction(
    @SerialName("block_height") val blockHeight: Int,
    @SerialName("block_time") val blockTime: Int,
    @SerialName("tx_hash") val txHash: String,
    @SerialName("tx_index") val txIndex: Int,
)
