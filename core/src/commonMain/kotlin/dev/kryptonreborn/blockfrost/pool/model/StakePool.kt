package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool
 *
 * @property poolId Bech32 encoded pool ID
 * @property hex Hexadecimal pool ID
 * @property activeStake Active delegated amount
 * @property liveStake Currently delegated amount
 */
@Serializable
data class StakePool(
    @SerialName("pool_id") val poolId: String,
    val hex: String,
    @SerialName("active_stake") val activeStake: String,
    @SerialName("live_stake") val liveStake: String,
)
