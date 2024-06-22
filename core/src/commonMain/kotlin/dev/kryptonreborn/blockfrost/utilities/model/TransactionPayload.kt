package dev.kryptonreborn.blockfrost.utilities.model

import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.utils.removeEmptyKeys
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * UTxO
 *
 * @property input Transaction input
 * @property output Transaction output
 */
data class UTxO(
    val input: TransactionInput,
    val output: TransactionOutput,
)

/**
 * Transaction Input
 *
 * @property txId Transaction ID
 * @property index Index
 */
data class TransactionInput(
    val txId: String,
    val index: Int,
)

/**
 * Transaction Output
 *
 * @property address Address
 * @property value Value
 * @property datumHash Datum hash
 * @property datum Datum
 * @property script Script
 */
data class TransactionOutput(
    val address: String,
    val value: TransactionOutputValue,
    val datumHash: String,
    val datum: Map<String, JsonElement>,
    val script: Map<String, JsonElement>,
)

/**
 * TxOutValue
 *
 * @property coins Coins
 * @property assets Assets
 */
data class TransactionOutputValue(
    val coins: Long,
    val assets: Map<String, Long>,
)

/**
 * Transaction Payload
 *
 * @property cbor CBOR
 * @property uTxO UTxO
 */
data class TransactionPayload(
    val cbor: String,
    val uTxO: UTxO? = null,
)

private fun TransactionPayload.toPayload() =
    JsonObject(
        mutableMapOf<String, JsonElement>(
            "cbor" to JsonPrimitive(cbor),
        ).apply {
            uTxO?.let {
                put(
                    "additionalUtxoSet",
                    JsonArray(
                        listOf(
                            JsonArray(
                                listOf(
                                    JsonObject(
                                        mapOf(
                                            "txId" to JsonPrimitive(uTxO.input.txId),
                                            "index" to JsonPrimitive(uTxO.input.index),
                                        ),
                                    ),
                                    JsonObject(
                                        mapOf(
                                            "address" to JsonPrimitive(uTxO.output.address),
                                            "value" to
                                                JsonObject(
                                                    mutableMapOf(
                                                        "coins" to JsonPrimitive(uTxO.output.value.coins),
                                                        "assets" to
                                                            JsonObject(
                                                                uTxO.output.value.assets.mapValues {
                                                                    JsonPrimitive(it.value)
                                                                },
                                                            ),
                                                    ),
                                                ),
                                            "datum_hash" to JsonPrimitive(uTxO.output.datumHash),
                                            "datum" to JsonObject(uTxO.output.datum),
                                            "script" to JsonObject(uTxO.output.script),
                                        ),
                                    ),
                                ),
                            ),
                        ),
                    ),
                )
            }
        },
    )

fun TransactionPayload.toNormalizedPayload() =
    Ktor.json.encodeToString(
        JsonObject.serializer(),
        toPayload().removeEmptyKeys(),
    )