package dev.kryptonreborn.blockfrost.scripts.model

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Script JSON
 *
 * @property json JSON contents of the timelock script, null for Plutus scripts
 */
@Serializable
data class ScriptJson(
    @SerialName("json") private val _json: JsonElement?,
) {
    val json: Any?
        get() = _json?.deserializeJsonElement()
}
