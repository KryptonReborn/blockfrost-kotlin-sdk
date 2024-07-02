package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Information
 *
 * @property poolId Bech32 pool ID
 * @property hex Hexadecimal pool ID
 * @property vrfKey VRF key hash
 * @property blocksMinted Total minted blocks
 * @property blocksEpoch Number of blocks minted in the current epoch
 * @property liveStake Live stake amount
 * @property liveSize Live size
 * @property liveSaturation Live saturation
 * @property liveDelegators Live delegators count
 * @property activeStake Active stake amount
 * @property activeSize Active size
 * @property declaredPledge Stake pool certificate pledge
 * @property livePledge Stake pool current pledge
 * @property marginCost Margin tax cost of the stake pool
 * @property fixedCost Fixed tax cost of the stake pool
 * @property rewardAccount Bech32 reward account of the stake pool
 * @property owners List of owners
 * @property registration List of registration transactions
 * @property retirement List of retirement transactions
 */
@Serializable
data class StakePoolInfo(
    @SerialName("pool_id") val poolId: String,
    val hex: String,
    @SerialName("vrf_key") val vrfKey: String,
    @SerialName("blocks_minted") val blocksMinted: Int,
    @SerialName("blocks_epoch") val blocksEpoch: Int,
    @SerialName("live_stake") val liveStake: String,
    @SerialName("live_size") val liveSize: Double,
    @SerialName("live_saturation") val liveSaturation: Double,
    @SerialName("live_delegators") val liveDelegators: Int,
    @SerialName("active_stake") val activeStake: String,
    @SerialName("active_size") val activeSize: Double,
    @SerialName("declared_pledge") val declaredPledge: String,
    @SerialName("live_pledge") val livePledge: String,
    @SerialName("margin_cost") val marginCost: Double,
    @SerialName("fixed_cost") val fixedCost: String,
    @SerialName("reward_account") val rewardAccount: String,
    val owners: List<String>,
    val registration: List<String>,
    val retirement: List<String>,
)
