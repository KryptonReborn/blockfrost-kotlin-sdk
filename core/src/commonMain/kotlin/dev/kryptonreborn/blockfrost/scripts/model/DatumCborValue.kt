package dev.kryptonreborn.blockfrost.scripts.model

import kotlinx.serialization.Serializable

/**
 * Datum CBOR Value
 *
 * @property cbor CBOR serialized datum
 */
@Serializable
data class DatumCborValue(
    val cbor: String,
)
