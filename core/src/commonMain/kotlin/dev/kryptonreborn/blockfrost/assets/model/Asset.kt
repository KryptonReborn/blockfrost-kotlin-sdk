package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    val asset: String,
    val quantity: String,
)
