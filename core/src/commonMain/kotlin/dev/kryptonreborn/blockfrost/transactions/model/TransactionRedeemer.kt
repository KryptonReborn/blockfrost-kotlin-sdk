package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction Redeemer
 *
 * @property txIndex Index of the redeemer within the transaction
 * @property purpose Validation purpose (spend, mint, cert, reward)
 * @property scriptHash Script hash
 * @property redeemerDataHash Redeemer data hash
 * @property datumHash Datum hash (deprecated)
 * @property unitMem The budget in Memory to run a script
 * @property unitSteps The budget in CPU steps to run a script
 * @property fee The fee consumed to run the script
 */
@Serializable
data class TransactionRedeemer(
    @SerialName("tx_index") val txIndex: Int,
    val purpose: String,
    @SerialName("script_hash") val scriptHash: String,
    @SerialName("redeemer_data_hash") val redeemerDataHash: String,
    @Deprecated("This field is deprecated and will be removed in future versions.")
    @SerialName("datum_hash") val datumHash: String? = null,
    @SerialName("unit_mem") val unitMem: String,
    @SerialName("unit_steps") val unitSteps: String,
    val fee: String,
)
