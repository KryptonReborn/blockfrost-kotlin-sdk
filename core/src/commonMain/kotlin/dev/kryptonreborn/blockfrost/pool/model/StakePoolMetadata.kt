package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Metadata
 *
 * @property poolId Bech32 pool ID
 * @property hex Hexadecimal pool ID
 * @property url URL to the stake pool metadata
 * @property hash Hash of the metadata file
 * @property ticker Ticker of the stake pool
 * @property name Name of the stake pool
 * @property description Description of the stake pool
 * @property homepage Home page of the stake pool
 */
@Serializable
data class StakePoolMetadata(
    @SerialName("pool_id") val poolId: String,
    val hex: String,
    val url: String?,
    val hash: String?,
    val ticker: String?,
    val name: String?,
    val description: String?,
    val homepage: String?,
)
