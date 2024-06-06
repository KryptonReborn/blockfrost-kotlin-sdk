package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountWithdrawalContent` class represents the withdrawal content of an account.
 *
 * @property txHash The transaction hash of the withdrawal.
 * @property amount The amount of the withdrawal.
 */
@Serializable
data class AccountWithdrawalContent(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("amount") val amount: String,
)
