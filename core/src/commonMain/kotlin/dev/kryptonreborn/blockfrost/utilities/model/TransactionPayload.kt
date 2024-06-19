package dev.kryptonreborn.blockfrost.utilities.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

sealed interface UtxoTuple

/**
 * Transaction Input
 *
 * @property txId Transaction ID
 * @property index Index
 */
@Serializable
data class TxIn(
    val txId: String,
    val index: Int,
) : UtxoTuple

/**
 * Transaction Output
 *
 * @property address Address
 * @property value Value
 * @property datumHash Datum hash
 * @property datum Datum
 * @property script Script
 */
@Serializable
data class TxOut(
    val address: String,
    val value: TxOutValue,
    @SerialName("datum_hash")
    val datumHash: String,
    val datum: Map<String, JsonElement>,
    val script: Map<String, JsonElement>,
) : UtxoTuple

/**
 * TxOutValue
 *
 * @property coins Coins
 * @property assets Assets
 */
@Serializable
data class TxOutValue(
    val coins: Int,
    val assets: Map<String, Int>,
)

@Serializable
data class TransactionPayload(
    val cbor: String,
    @Serializable(with = UtxoTupleListSerializer::class)
    val additionalUtxoSet: List<List<UtxoTuple>>,
)

object UtxoTupleListSerializer : KSerializer<List<List<UtxoTuple>>> {
    override val descriptor: SerialDescriptor = ListSerializer(ListSerializer(UtxoTupleSerializer)).descriptor

    override fun serialize(
        encoder: Encoder,
        value: List<List<UtxoTuple>>,
    ) {
        ListSerializer(ListSerializer(UtxoTupleSerializer)).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): List<List<UtxoTuple>> {
        return ListSerializer(ListSerializer(UtxoTupleSerializer)).deserialize(decoder)
    }
}

object UtxoTupleSerializer : KSerializer<UtxoTuple> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("UtxoItem") {
            element<String>("txId", isOptional = true)
            element<String>("address", isOptional = true)
        }

    override fun serialize(
        encoder: Encoder,
        value: UtxoTuple,
    ) {
        when (value) {
            is TxIn -> encoder.encodeSerializableValue(TxIn.serializer(), value)
            is TxOut -> encoder.encodeSerializableValue(TxOut.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): UtxoTuple {
        val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException("This class can be loaded only by JSON")
        val jsonElement = jsonDecoder.decodeJsonElement()
        val jsonObject = jsonElement as? JsonObject ?: throw SerializationException("Expected JsonObject")

        return when {
            "txId" in jsonObject -> jsonDecoder.json.decodeFromJsonElement(TxIn.serializer(), jsonElement)
            "address" in jsonObject -> jsonDecoder.json.decodeFromJsonElement(TxOut.serializer(), jsonElement)
            else -> throw SerializationException("Unknown type")
        }
    }
}
