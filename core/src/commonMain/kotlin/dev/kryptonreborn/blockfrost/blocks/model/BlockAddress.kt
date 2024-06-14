package dev.kryptonreborn.blockfrost.blocks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockAddress(
    @SerialName("address")
    val address: String,
    @SerialName("transactions")
    val transactions: List<Transaction>,
) {
    @Serializable
    data class Transaction(
        @SerialName("tx_hash")
        val txHash: String,
    )
}
