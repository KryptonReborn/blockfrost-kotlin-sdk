package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Retirement Certificate
 *
 * @property certIndex Index of the certificate within the transaction
 * @property poolId Bech32 stake pool ID
 * @property retiringEpoch Epoch in which the pool becomes retired
 */
@Serializable
data class StakePoolRetirementCertificate(
    @SerialName("cert_index") val certIndex: Int,
    @SerialName("pool_id") val poolId: String,
    @SerialName("retiring_epoch") val retiringEpoch: Int,
)
