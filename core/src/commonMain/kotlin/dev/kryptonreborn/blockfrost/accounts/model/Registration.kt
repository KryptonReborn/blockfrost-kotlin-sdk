package dev.kryptonreborn.blockfrost.accounts.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Registration(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("action") val action: String,
)
