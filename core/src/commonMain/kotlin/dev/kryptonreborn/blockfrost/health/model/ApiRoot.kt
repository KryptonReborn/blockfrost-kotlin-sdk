package dev.kryptonreborn.blockfrost.health.model

import kotlinx.serialization.Serializable

/**
 * The `ApiRoot` class represents the root information of the Blockfrost API.
 *
 * @property url The base URL of the API.
 * @property version The version of the API.
 */
@Serializable
data class ApiRoot(
    /**
     * The base URL of the API.
     */
    val url: String,
    /**
     * The version of the API.
     */
    val version: String,
)
