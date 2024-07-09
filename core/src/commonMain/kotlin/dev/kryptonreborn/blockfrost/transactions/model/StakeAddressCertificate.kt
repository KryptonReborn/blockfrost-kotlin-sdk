package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Address Certificate
 *
 * @property certIndex Index of the certificate within the transaction
 * @property address Delegation stake address
 * @property registration Registration boolean, false if deregistration
 */
@Serializable
data class StakeAddressCertificate(
    @SerialName("cert_index") val certIndex: Int,
    val address: String,
    val registration: Boolean,
)
