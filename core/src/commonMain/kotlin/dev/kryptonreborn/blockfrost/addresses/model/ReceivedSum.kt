package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.Serializable

@Serializable
data class ReceivedSum(
    val quantity: String = "",
    val unit: String = "",
)
