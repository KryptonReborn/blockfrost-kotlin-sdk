package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressUTXO(
    val address: String,
    val amount: List<Amount>,
    val block: String,
    @SerialName("data_hash") val dataHash: String?,
    @SerialName("inline_datum") val inlineDatum: String?,
    @SerialName("output_index") val outputIndex: Int = 0,
    @SerialName("reference_script_hash") val referenceScriptHash: String?,
    @SerialName("tx_hash") val txHash: String,
)
