package dev.kryptonreborn.blockfrost.metadata.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction Metadata Content in CBOR
 *
 * @property txHash Transaction hash that contains the specific metadata
 * @property cborMetadata Content of the CBOR metadata (deprecated)
 * @property metadata Content of the CBOR metadata in hex
 */
@Serializable
data class TransactionMetadataContentCBOR(
    @SerialName("tx_hash") val txHash: String,
    @Deprecated("Use metadata instead", ReplaceWith("metadata"))
    @SerialName("cbor_metadata") val cborMetadata: String?,
    val metadata: String?,
)
