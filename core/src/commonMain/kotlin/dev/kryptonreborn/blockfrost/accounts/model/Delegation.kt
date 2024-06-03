package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Delegation(
    @SerialName("active_epoch") val activeEpoch: Int,
    @SerialName("amount") val amount: String,
    @SerialName("pool_id") val poolId: String,
)
