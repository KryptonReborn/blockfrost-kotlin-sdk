package dev.kryptonreborn.blockfrost.health.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `Clock` class represents the current server time from the Blockfrost API.
 *
 * @property serverTime The current server time in UNIX time milliseconds.
 */
@Serializable
data class Clock(
    /**
     * The current server time in UNIX time milliseconds.
     */
    @SerialName("server_time")
    val serverTime: Long,
)
