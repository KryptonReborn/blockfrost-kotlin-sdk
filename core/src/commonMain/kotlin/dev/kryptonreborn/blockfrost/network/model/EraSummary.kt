package dev.kryptonreborn.blockfrost.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Era Summary
 *
 * @property start Start of the blockchain era, relative to the start of the network
 * @property end End of the blockchain era, relative to the start of the network
 * @property parameters Era parameters
 */
@Serializable
data class EraSummary(
    val start: EraBoundary,
    val end: EraBoundary,
    val parameters: EraParameters,
)

/**
 * Era Boundary
 *
 * @property time Time in seconds relative to the start time of the network
 * @property slot Absolute slot number
 * @property epoch Epoch number
 */
@Serializable
data class EraBoundary(
    val time: Long,
    val slot: Int,
    val epoch: Int,
)

/**
 * Era Parameters
 *
 * @property epochLength Epoch length in number of slots
 * @property slotLength Slot length in seconds
 * @property safeZone Zone in which it is guaranteed that no hard fork can take place
 */
@Serializable
data class EraParameters(
    @SerialName("epoch_length") val epochLength: Int,
    @SerialName("slot_length") val slotLength: Double,
    @SerialName("safe_zone") val safeZone: Int,
)
