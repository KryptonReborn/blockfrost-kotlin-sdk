package dev.kryptonreborn.blockfrost.blocks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @property address Address of the stake pool
 * @property transactions List of transactions
 */
@Serializable
data class BlockAddress(
    val address: String,
    val transactions: List<Transaction>,
) {
    @Serializable
    data class Transaction(
        @SerialName("tx_hash") val txHash: String,
    )
}
