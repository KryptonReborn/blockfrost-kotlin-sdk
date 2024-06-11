package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.Serializable

@Serializable
data class Amount(
    val quantity: String,
    val unit: String,
)
