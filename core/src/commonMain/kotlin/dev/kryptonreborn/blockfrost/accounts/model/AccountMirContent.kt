package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountMirContent` class represents the MIR content of an account.
 *
 * @property txHash The transaction hash of the MIR.
 * @property amount The amount of the MIR.
 */
@Serializable
data class AccountMirContent(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("amount") val amount: String,
)
