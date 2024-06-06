package dev.kryptonreborn.blockfrost.accounts.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountRegistrationContent` class represents the registration content of an account.
 *
 * @property txHash The transaction hash of the registration.
 * @property action The action of the registration.
 */
@Serializable
data class AccountRegistrationContent(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("action") val action: String,
)
