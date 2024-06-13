package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountContent` class represents the content of an account.
 *
 * @property stakeAddress The stake address of the account.
 * @property active Indicates whether the account is active.
 * @property activeEpoch The epoch in which the account became active.
 * @property controlledAmount The controlled amount of the account.
 * @property rewardsSum The sum of rewards of the account.
 * @property withdrawalsSum The sum of withdrawals of the account.
 * @property reservesSum The sum of reserves of the account.
 * @property treasurySum The sum of treasury of the account.
 * @property withdrawableAmount The withdrawable amount of the account.
 */
@Serializable
data class AccountContent(
    @SerialName("stake_address") val stakeAddress: String,
    @SerialName("active") val active: Boolean,
    @SerialName("active_epoch") val activeEpoch: Int?,
    @SerialName("controlled_amount") val controlledAmount: String,
    @SerialName("rewards_sum") val rewardsSum: String,
    @SerialName("withdrawals_sum") val withdrawalsSum: String,
    @SerialName("reserves_sum") val reservesSum: String,
    @SerialName("treasury_sum") val treasurySum: String,
    @SerialName("withdrawable_amount") val withdrawableAmount: String,
    @SerialName("pool_id") val poolId: String?,
)
