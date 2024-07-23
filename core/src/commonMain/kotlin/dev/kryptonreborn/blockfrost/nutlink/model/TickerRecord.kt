package dev.kryptonreborn.blockfrost.nutlink.model

import dev.kryptonreborn.blockfrost.utils.deserializeJsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Ticker Record
 *
 * @property txHash Hash of the transaction
 * @property blockHeight Block height of the record
 * @property txIndex Transaction index within the block
 * @property _payload Content of the ticker
 */
@Serializable
data class TickerRecord(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("block_height") val blockHeight: Int,
    @SerialName("tx_index") val txIndex: Int,
    @SerialName("payload") private val _payload: JsonElement,
) {
    val payload: Any?
        get() = _payload.deserializeJsonElement()
}
