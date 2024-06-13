package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Serializable

@Serializable
data class AssetAddress(
    val address: String,
    val quantity: String,
)
