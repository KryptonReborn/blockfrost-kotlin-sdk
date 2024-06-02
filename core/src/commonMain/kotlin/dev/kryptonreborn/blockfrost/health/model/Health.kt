package dev.kryptonreborn.blockfrost.health.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Health(
    @SerialName("is_healthy")
    val isHealthy: Boolean,
)
