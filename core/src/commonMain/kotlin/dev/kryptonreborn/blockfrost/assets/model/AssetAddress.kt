package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Serializable

/**
 *
 * @property address Address containing the specific asset
 * @property quantity Asset quantity on the specific address
 */
@Serializable
data class AssetAddress(
    val address: String,
    val quantity: String,
)
