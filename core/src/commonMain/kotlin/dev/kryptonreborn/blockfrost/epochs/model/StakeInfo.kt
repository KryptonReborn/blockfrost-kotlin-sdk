package dev.kryptonreborn.blockfrost.epochs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Info
 *
 * @property stakeAddress Stake address
 * @property poolId Bech32 prefix of the pool delegated to
 * @property amount Amount of active delegated stake in Lovelaces
 */
@Serializable
data class StakeInfo(
    @SerialName("stake_address") val stakeAddress: String,
    @SerialName("pool_id") val poolId: String,
    val amount: String,
)
