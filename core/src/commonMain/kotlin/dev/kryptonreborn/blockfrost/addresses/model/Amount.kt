package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.Serializable

/**
 * The `Amount` class represents an amount of an address from the Blockfrost API.
 *
 * @property quantity The quantity of the amount.
 * @property unit The unit of the amount.
 */
@Serializable
data class Amount(
    val quantity: String,
    val unit: String,
)
