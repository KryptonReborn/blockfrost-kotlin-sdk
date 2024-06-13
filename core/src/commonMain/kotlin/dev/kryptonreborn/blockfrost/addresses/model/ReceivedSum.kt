package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.Serializable

/**
 * The `ReceivedSum` class represents a received sum of an address from the Blockfrost API.
 * @property quantity The quantity of the received sum.
 * @property unit The unit of the received sum.
 */
@Serializable
data class ReceivedSum(
    val quantity: String,
    val unit: String,
)
