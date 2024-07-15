package dev.kryptonreborn.blockfrost.base.model

import kotlinx.serialization.Serializable

/**
 * Amount
 *
 * @property unit The unit of the value
 * @property quantity The quantity of the unit
 */
@Serializable
data class Amount(
    val unit: String,
    val quantity: String,
)
