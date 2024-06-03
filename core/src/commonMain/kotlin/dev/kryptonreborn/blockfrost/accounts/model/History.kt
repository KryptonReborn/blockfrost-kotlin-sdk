package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class History(
    @SerialName("active_epoch") val activeEpoch: Int,
    @SerialName("amount") val amount: String,
    @SerialName("pool_id") val poolId: String,
)
