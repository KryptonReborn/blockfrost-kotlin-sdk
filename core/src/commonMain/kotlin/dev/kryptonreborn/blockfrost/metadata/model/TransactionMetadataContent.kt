package dev.kryptonreborn.blockfrost.metadata.model

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Transaction Metadata Content
 *
 * @property txHash Transaction hash that contains the specific metadata
 * @property jsonMetadata Content of the JSON metadata
 */
@Serializable
data class TransactionMetadataContent(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("json_metadata") private val _jsonMetadata: JsonElement?,
) {
    val jsonMetadata: Any?
        get() = _jsonMetadata?.deserializeJsonElement()
}
