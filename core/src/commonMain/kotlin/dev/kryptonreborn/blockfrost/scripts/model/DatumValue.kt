package dev.kryptonreborn.blockfrost.scripts.model

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Datum Value
 *
 * @property jsonValue JSON content of the datum
 */
@Serializable
data class DatumValue(
    @SerialName("json_value") private val _jsonValue: JsonElement,
) {
    val jsonValue: Any?
        get() = _jsonValue.deserializeJsonElement()
}
