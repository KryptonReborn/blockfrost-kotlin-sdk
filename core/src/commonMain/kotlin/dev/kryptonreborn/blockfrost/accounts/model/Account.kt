package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    @SerialName("stake_address") val stakeAddress: String,
    @SerialName("active") val active: Boolean,
    @SerialName("active_epoch") val activeEpoch: Int,
    @SerialName("controlled_amount") val controlledAmount: String,
    @SerialName("rewards_sum") val rewardsSum: String,
    @SerialName("withdrawals_sum") val withdrawalsSum: String,
    @SerialName("reserves_sum") val reservesSum: String,
    @SerialName("treasury_sum") val treasurySum: String,
    @SerialName("withdrawable_amount") val withdrawableAmount: String,
)
