package dev.kryptonreborn.blockfrost.scripts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Script
 *
 * @property scriptHash Script hash
 */
@Serializable
data class Script(
    @SerialName("script_hash") val scriptHash: String,
)
