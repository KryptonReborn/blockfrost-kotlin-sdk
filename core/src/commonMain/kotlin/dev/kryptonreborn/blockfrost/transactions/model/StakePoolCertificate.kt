package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stake Pool Certificate
 *
 * @property certIndex Index of the certificate within the transaction
 * @property poolId Bech32 encoded pool ID
 * @property vrfKey VRF key hash
 * @property pledge Stake pool certificate pledge in Lovelaces
 * @property marginCost Margin tax cost of the stake pool
 * @property fixedCost Fixed tax cost of the stake pool in Lovelaces
 * @property rewardAccount Bech32 reward account of the stake pool
 * @property owners List of owners
 * @property metadata Metadata of the stake pool
 * @property relays List of relays
 * @property activeEpoch Epoch in which the update becomes active
 */
@Serializable
data class StakePoolCertificate(
    @SerialName("cert_index") val certIndex: Int,
    @SerialName("pool_id") val poolId: String,
    @SerialName("vrf_key") val vrfKey: String,
    val pledge: String,
    @SerialName("margin_cost") val marginCost: Double,
    @SerialName("fixed_cost") val fixedCost: String,
    @SerialName("reward_account") val rewardAccount: String,
    val owners: List<String>,
    val metadata: Metadata?,
    val relays: List<Relay>,
    @SerialName("active_epoch") val activeEpoch: Int,
)

/**
 * Metadata
 *
 * @property url URL to the stake pool metadata
 * @property hash Hash of the metadata file
 * @property ticker Ticker of the stake pool
 * @property name Name of the stake pool
 * @property description Description of the stake pool
 * @property homepage Home page of the stake pool
 */
@Serializable
data class Metadata(
    val url: String?,
    val hash: String?,
    val ticker: String?,
    val name: String?,
    val description: String?,
    val homepage: String?,
)

/**
 * Relay
 *
 * @property ipv4 IPv4 address of the relay
 * @property ipv6 IPv6 address of the relay
 * @property dns DNS name of the relay
 * @property dnsSrv DNS SRV entry of the relay
 * @property port Network port of the relay
 */
@Serializable
data class Relay(
    val ipv4: String?,
    val ipv6: String?,
    val dns: String?,
    @SerialName("dns_srv") val dnsSrv: String?,
    val port: Int,
)
