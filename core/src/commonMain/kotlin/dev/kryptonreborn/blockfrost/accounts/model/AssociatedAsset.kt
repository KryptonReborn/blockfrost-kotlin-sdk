package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssociatedAsset(
    @SerialName("unit") val unit: String,
    @SerialName("quantity") val quantity: String,
)
