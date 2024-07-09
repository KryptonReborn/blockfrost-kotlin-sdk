package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction Metadata CBOR
 *
 * @property label Metadata label
 * @property cborMetadata Content of the CBOR metadata (deprecated)
 * @property metadata Content of the CBOR metadata in hex
 */
@Serializable
data class TransactionMetadataCbor(
    val label: String,
    @SerialName("cbor_metadata") val cborMetadata: String?,
    val metadata: String?,
)
