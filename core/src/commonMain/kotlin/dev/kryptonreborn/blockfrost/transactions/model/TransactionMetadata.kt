package dev.kryptonreborn.blockfrost.transactions.model

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Transaction Metadata
 *
 * @property label Metadata label
 * @property _jsonMetadata Content of the metadata
 */
@Serializable
data class TransactionMetadata(
    val label: String,
    @SerialName("json_metadata") private val _jsonMetadata: JsonElement,
) {
    val jsonMetadata: Any?
        get() = _jsonMetadata.deserializeJsonElement()
}
