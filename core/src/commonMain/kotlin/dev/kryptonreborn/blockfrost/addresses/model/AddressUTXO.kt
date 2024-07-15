package dev.kryptonreborn.blockfrost.addresses.model

import dev.kryptonreborn.blockfrost.base.model.Amount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AddressUTXO` class represents an UTXO of an address from the Blockfrost API.
 *
 * @property address The address of the UTXO.
 * @property amount The amount of the UTXO.
 * @property block The block of the UTXO.
 * @property dataHash The data hash of the UTXO.
 * @property inlineDatum The inline datum of the UTXO.
 * @property outputIndex The output index of the UTXO.
 * @property referenceScriptHash The reference script hash of the UTXO.
 * @property txHash The transaction hash of the UTXO.
 */
@Serializable
data class AddressUTXO(
    val address: String,
    val amount: List<Amount>,
    val block: String,
    @SerialName("data_hash") val dataHash: String?,
    @SerialName("inline_datum") val inlineDatum: String?,
    @SerialName("output_index") val outputIndex: Long,
    @SerialName("reference_script_hash") val referenceScriptHash: String?,
    @SerialName("tx_hash") val txHash: String,
)
