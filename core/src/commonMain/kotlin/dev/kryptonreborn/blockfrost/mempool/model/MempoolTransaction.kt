package dev.kryptonreborn.blockfrost.mempool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Mempool Transaction
 *
 * @property txHash Hash of the transaction
 */
@Serializable
data class MempoolTransaction(
    @SerialName("tx_hash") val txHash: String,
)
