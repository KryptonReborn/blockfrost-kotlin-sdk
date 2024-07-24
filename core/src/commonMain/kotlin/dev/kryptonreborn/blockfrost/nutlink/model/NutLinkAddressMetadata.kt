package dev.kryptonreborn.blockfrost.nutlink.model

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * NutLink Address Metadata
 *
 * @property address Bech32 encoded address
 * @property metadataUrl URL of the specific metadata file
 * @property metadataHash Hash of the metadata file
 * @property _metadata The cached metadata of the metadata_url file
 */
@Serializable
data class NutLinkAddressMetadata(
    val address: String,
    @SerialName("metadata_url") val metadataUrl: String,
    @SerialName("metadata_hash") val metadataHash: String,
    @SerialName("metadata") private val _metadata: JsonElement?,
) {
    val metadata: Any?
        get() = _metadata?.deserializeJsonElement()
}
