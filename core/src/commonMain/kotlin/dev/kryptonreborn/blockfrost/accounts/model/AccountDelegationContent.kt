package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountDelegationContent` class represents the delegation content of an account.
 *
 * @property activeEpoch The active epoch of the account.
 * @property amount The amount of the delegation.
 * @property poolId The pool ID of the delegation.
 */
@Serializable
data class AccountDelegationContent(
    @SerialName("active_epoch") val activeEpoch: Int,
    @SerialName("amount") val amount: String,
    @SerialName("pool_id") val poolId: String,
)
