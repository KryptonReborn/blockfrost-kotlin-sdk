package dev.kryptonreborn.blockfrost.nutlink.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Oracle Ticker
 *
 * @property name Name of the ticker
 * @property count Number of ticker records
 * @property latestBlock Block height of the latest record
 */
@Serializable
data class OracleTicker(
    val name: String,
    val count: Int,
    @SerialName("latest_block") val latestBlock: Int,
)
