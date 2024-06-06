package dev.kryptonreborn.blockfrost.health.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `Health` class represents the health status of the Blockfrost API.
 *
 * @property isHealthy Indicates whether the API is healthy.
 */
@Serializable
data class Health(
    /**
     * Indicates whether the API is healthy.
     */
    @SerialName("is_healthy")
    val isHealthy: Boolean,
)
