package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reward(
    @SerialName("epoch") val epoch: Int,
    @SerialName("amount") val amount: String,
    @SerialName("pool_id") val poolId: String,
    @SerialName("type") val type: String,
)
