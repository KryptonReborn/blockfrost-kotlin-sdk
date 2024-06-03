package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Withdrawal(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("amount") val amount: String,
)
