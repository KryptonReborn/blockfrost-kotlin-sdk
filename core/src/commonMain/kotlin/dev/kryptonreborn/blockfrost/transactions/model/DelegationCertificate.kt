package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Delegation Certificate
 *
 * @property index Index of the certificate within the transaction (deprecated)
 * @property certIndex Index of the certificate within the transaction
 * @property address Bech32 delegation stake address
 * @property poolId Bech32 ID of delegated stake pool
 * @property activeEpoch Epoch in which the delegation becomes active
 */
@Serializable
data class DelegationCertificate(
    @Deprecated("This field is deprecated and will be removed in future versions.")
    val index: Int? = null,
    @SerialName("cert_index") val certIndex: Int,
    val address: String,
    @SerialName("pool_id") val poolId: String,
    @SerialName("active_epoch") val activeEpoch: Int,
)
