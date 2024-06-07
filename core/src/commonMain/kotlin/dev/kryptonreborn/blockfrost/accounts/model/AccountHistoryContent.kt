package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountHistoryContent` class represents the history content of an account.
 *
 * @property activeEpoch The active epoch of the account.
 * @property amount The amount of the history.
 * @property poolId The pool ID of the history.
 */
@Serializable
data class AccountHistoryContent(
    @SerialName("active_epoch") val activeEpoch: Int,
    @SerialName("amount") val amount: String,
    @SerialName("pool_id") val poolId: String,
)
