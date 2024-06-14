package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Serializable

/**
 *
 * @property asset Asset identifier
 * @property quantity Current asset quantity
 */
@Serializable
data class Asset(
    val asset: String,
    val quantity: String,
)
