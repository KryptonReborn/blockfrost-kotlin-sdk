package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Delegator
 *
 * @property address Bech32 encoded stake address
 * @property liveStake Currently delegated amount
 */
@Serializable
data class StakePoolDelegator(
    val address: String,
    @SerialName("live_stake") val liveStake: String,
)
