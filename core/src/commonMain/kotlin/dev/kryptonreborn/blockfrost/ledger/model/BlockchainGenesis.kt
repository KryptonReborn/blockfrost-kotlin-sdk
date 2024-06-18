package dev.kryptonreborn.blockfrost.ledger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Blockchain Genesis
 *
 * @property activeSlotsCoefficient The proportion of slots in which blocks should be issued
 * @property updateQuorum Determines the quorum needed for votes on the protocol parameter updates
 * @property maxLovelaceSupply The total number of lovelace in the system
 * @property networkMagic Network identifier
 * @property epochLength Number of slots in an epoch
 * @property systemStart Time of slot 0 in UNIX time
 * @property slotsPerKesPeriod Number of slots in an KES period
 * @property slotLength Duration of one slot in seconds
 * @property maxKesEvolutions The maximum number of time a KES key can be evolved before a pool operator must create a new operational certificate
 * @property securityParam Security parameter k
 */
@Serializable
data class BlockchainGenesis(
    @SerialName("active_slots_coefficient") val activeSlotsCoefficient: Double,
    @SerialName("update_quorum") val updateQuorum: Int,
    @SerialName("max_lovelace_supply") val maxLovelaceSupply: String,
    @SerialName("network_magic") val networkMagic: Int,
    @SerialName("epoch_length") val epochLength: Int,
    @SerialName("system_start") val systemStart: Int,
    @SerialName("slots_per_kes_period") val slotsPerKesPeriod: Int,
    @SerialName("slot_length") val slotLength: Int,
    @SerialName("max_kes_evolutions") val maxKesEvolutions: Int,
    @SerialName("security_param") val securityParam: Int,
)
