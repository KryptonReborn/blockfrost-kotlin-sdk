package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Retired Stake Pool
 *
 * @property poolId Bech32 encoded pool ID
 * @property epoch Retirement epoch number
 */
@Serializable
data class StakePoolRetire(
    @SerialName("pool_id") val poolId: String,
    val epoch: Int,
)
