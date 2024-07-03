package dev.kryptonreborn.blockfrost.scripts.model

import dev.kryptonreborn.blockfrost.base.model.RedeemerPurpose
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Script Redeemer
 *
 * @property txHash Hash of the transaction
 * @property txIndex The index of the redeemer pointer in the transaction
 * @property purpose Validation purpose (spend, mint, cert, reward)
 * @property redeemerDataHash Datum hash of the redeemer
 * @property datumHash Datum hash (deprecated)
 * @property unitMem The budget in Memory to run a script
 * @property unitSteps The budget in CPU steps to run a script
 * @property fee The fee consumed to run the script
 */
@Serializable
data class ScriptRedeemer(
    @SerialName("tx_hash") val txHash: String,
    @SerialName("tx_index") val txIndex: Int,
    val purpose: RedeemerPurpose,
    @SerialName("redeemer_data_hash") val redeemerDataHash: String,
    @Deprecated("Use redeemerDataHash instead")
    @SerialName("datum_hash") val datumHash: String? = null,
    @SerialName("unit_mem") val unitMem: String,
    @SerialName("unit_steps") val unitSteps: String,
    val fee: String,
)
