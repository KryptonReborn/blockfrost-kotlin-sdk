package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction MIR
 *
 * @property pot Source of MIR funds (reserve, treasury)
 * @property certIndex Index of the certificate within the transaction
 * @property address Bech32 stake address
 * @property amount MIR amount in Lovelaces
 */
@Serializable
data class TransactionMIR(
    val pot: String,
    @SerialName("cert_index") val certIndex: Int,
    val address: String,
    val amount: String,
)
