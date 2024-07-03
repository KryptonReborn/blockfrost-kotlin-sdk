package dev.kryptonreborn.blockfrost.scripts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Specific Script
 *
 * @property scriptHash Script hash
 * @property type Type of the script language (timelock, plutusV1, plutusV2)
 * @property serialisedSize The size of the CBOR serialised script, if a Plutus script
 */
@Serializable
data class SpecificScript(
    @SerialName("script_hash") val scriptHash: String,
    val type: String,
    @SerialName("serialised_size") val serialisedSize: Int?,
)
