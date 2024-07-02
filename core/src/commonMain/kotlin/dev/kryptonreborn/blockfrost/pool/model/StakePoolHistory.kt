package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool History
 *
 * @property epoch Epoch number
 * @property blocks Number of blocks created by pool
 * @property activeStake Active (Snapshot of live stake 2 epochs ago) stake in Lovelaces
 * @property activeSize Pool size (percentage) of overall active stake at that epoch
 * @property delegatorsCount Number of delegators for epoch
 * @property rewards Total rewards received before distribution to delegators
 * @property fees Pool operator rewards
 */
@Serializable
data class StakePoolHistory(
    val epoch: Int,
    val blocks: Int,
    @SerialName("active_stake") val activeStake: String,
    @SerialName("active_size") val activeSize: Double,
    @SerialName("delegators_count") val delegatorsCount: Int,
    val rewards: String,
    val fees: String,
)
