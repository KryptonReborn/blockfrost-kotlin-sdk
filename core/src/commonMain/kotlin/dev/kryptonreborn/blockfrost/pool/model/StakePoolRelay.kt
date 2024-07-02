package dev.kryptonreborn.blockfrost.pool.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Relay
 *
 * @property ipv4 IPv4 address of the relay
 * @property ipv6 IPv6 address of the relay
 * @property dns DNS name of the relay
 * @property dnsSrv DNS SRV entry of the relay
 * @property port Network port of the relay
 */
@Serializable
data class StakePoolRelay(
    val ipv4: String?,
    val ipv6: String?,
    val dns: String?,
    @SerialName("dns_srv") val dnsSrv: String?,
    val port: Int,
)
