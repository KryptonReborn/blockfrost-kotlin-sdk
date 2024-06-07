package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountRewardContent` class represents the reward content of an account.
 *
 * @property epoch The epoch of the reward.
 * @property amount The amount of the reward.
 * @property poolId The pool ID of the reward.
 * @property type The type of the reward.
 */
@Serializable
data class AccountRewardContent(
    @SerialName("epoch") val epoch: Int,
    @SerialName("amount") val amount: String,
    @SerialName("pool_id") val poolId: String,
    @SerialName("type") val type: String,
)
