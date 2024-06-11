package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.Serializable

/**
 * The `SentSum` class represents a sent sum of an address from the Blockfrost API.
 *
 * @property quantity The quantity of the sent sum.
 * @property unit The unit of the sent sum.
 */
@Serializable
data class SentSum(
    val quantity: String,
    val unit: String,
)
