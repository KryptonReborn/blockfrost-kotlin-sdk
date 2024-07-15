package dev.kryptonreborn.blockfrost.transactions.model

import dev.kryptonreborn.blockfrost.base.model.Amount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction UTXOs
 *
 * @property hash Transaction hash
 * @property inputs List of transaction inputs
 * @property outputs List of transaction outputs
 */
@Serializable
data class TransactionUtxos(
    val hash: String,
    val inputs: List<TransactionInput>,
    val outputs: List<TransactionOutput>,
)

/**
 * Transaction Input
 *
 * @property address Input address
 * @property amount List of input amounts
 * @property txHash Hash of the UTXO transaction
 * @property outputIndex UTXO index in the transaction
 * @property dataHash The hash of the transaction output datum
 * @property inlineDatum CBOR encoded inline datum
 * @property referenceScriptHash The hash of the reference script of the input
 * @property collateral Whether the input is a collateral consumed on script validation failure
 * @property reference Whether the input is a reference transaction input
 */
@Serializable
data class TransactionInput(
    val address: String,
    val amount: List<Amount>,
    @SerialName("tx_hash") val txHash: String,
    @SerialName("output_index") val outputIndex: Int,
    @SerialName("data_hash") val dataHash: String?,
    @SerialName("inline_datum") val inlineDatum: String?,
    @SerialName("reference_script_hash") val referenceScriptHash: String?,
    val collateral: Boolean,
    val reference: Boolean,
)

/**
 * Transaction Output
 *
 * @property address Output address
 * @property amount List of output amounts
 * @property outputIndex UTXO index in the transaction
 * @property dataHash The hash of the transaction output datum
 * @property inlineDatum CBOR encoded inline datum
 * @property collateral Whether the output is a collateral output
 * @property referenceScriptHash The hash of the reference script of the output
 */
@Serializable
data class TransactionOutput(
    val address: String,
    val amount: List<Amount>,
    @SerialName("output_index") val outputIndex: Int,
    @SerialName("data_hash") val dataHash: String?,
    @SerialName("inline_datum") val inlineDatum: String?,
    val collateral: Boolean,
    @SerialName("reference_script_hash") val referenceScriptHash: String?,
)
