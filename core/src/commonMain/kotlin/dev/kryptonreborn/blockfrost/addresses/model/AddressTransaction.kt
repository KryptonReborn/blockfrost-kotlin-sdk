package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AddressTransaction` class represents a transaction of an address from the Blockfrost API.
 * @property blockHeight The block height of the transaction.
 * @property blockTime The block time of the transaction.
 * @property txHash The transaction hash of the transaction.
 * @property txIndex The transaction index of the transaction.

 */
@Serializable
data class AddressTransaction(
    @SerialName("block_height") val blockHeight: Long,
    @SerialName("block_time") val blockTime: Long,
    @SerialName("tx_hash") val txHash: String,
    @SerialName("tx_index") val txIndex: Long,
)
