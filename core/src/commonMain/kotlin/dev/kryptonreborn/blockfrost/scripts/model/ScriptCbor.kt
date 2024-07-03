package dev.kryptonreborn.blockfrost.scripts.model

import kotlinx.serialization.Serializable

/**
 * Script CBOR
 *
 * @property cbor CBOR contents of the Plutus script, null for timelocks
 */
@Serializable
data class ScriptCbor(
    val cbor: String?,
)
