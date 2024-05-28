package dev.kryptonreborn.blockfrost.health.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Clock(
    @SerialName("server_time")
    val serverTime: Long,
)
