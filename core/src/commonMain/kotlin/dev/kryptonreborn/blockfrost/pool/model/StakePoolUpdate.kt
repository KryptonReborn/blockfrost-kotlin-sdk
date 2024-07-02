package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Update
 *
 * @property txHash Transaction ID
 * @property certIndex Certificate within the transaction
 * @property action Action in the certificate (registered or deregistered)
 */
@Serializable
data class StakePoolUpdate(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("cert_index") val certIndex: Int,
    val action: String,
)
