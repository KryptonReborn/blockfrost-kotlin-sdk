package dev.kryptonreborn.blockfrost.mempool.model

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction Details
 *
 * @property tx Transaction data
 * @property inputs List of transaction inputs
 * @property outputs List of transaction outputs
 * @property redeemers List of redeemers
 */
@Serializable
data class MempoolTransactionDetails(
    val tx: TransactionData,
    val inputs: List<TransactionInput>,
    val outputs: List<TransactionOutput>,
    val redeemers: List<Redeemer>? = null,
)

/**
 * Transaction Data
 *
 * @property hash Transaction hash
 * @property outputAmount List of output amounts
 * @property fees Fees of the transaction in Lovelaces
 * @property deposit Deposit within the transaction in Lovelaces
 * @property size Size of the transaction in Bytes
 * @property invalidBefore Left (included) endpoint of the timelock validity intervals
 * @property invalidHereafter Right (excluded) endpoint of the timelock validity intervals
 * @property utxoCount Count of UTXOs within the transaction
 * @property withdrawalCount Count of the withdrawals within the transaction
 * @property mirCertCount Count of the MIR certificates within the transaction
 * @property delegationCount Count of the delegations within the transaction
 * @property stakeCertCount Count of the stake keys (de)registration within the transaction
 * @property poolUpdateCount Count of the stake pool registration and update certificates within the transaction
 * @property poolRetireCount Count of the stake pool retirement certificates within the transaction
 * @property assetMintOrBurnCount Count of asset mints and burns within the transaction
 * @property redeemerCount Count of redeemers within the transaction
 * @property validContract True if contract script passed validation
 */
@Serializable
data class TransactionData(
    val hash: String,
    @SerialName("output_amount") val outputAmount: List<OutputAmount>,
    val fees: @Contextual BigInteger,
    val deposit: @Contextual BigInteger,
    val size: Int,
    @SerialName("invalid_before") val invalidBefore: Long?,
    @SerialName("invalid_hereafter") val invalidHereafter: Long?,
    @SerialName("utxo_count") val utxoCount: Int,
    @SerialName("withdrawal_count") val withdrawalCount: Int,
    @SerialName("mir_cert_count") val mirCertCount: Int,
    @SerialName("delegation_count") val delegationCount: Int,
    @SerialName("stake_cert_count") val stakeCertCount: Int,
    @SerialName("pool_update_count") val poolUpdateCount: Int,
    @SerialName("pool_retire_count") val poolRetireCount: Int,
    @SerialName("asset_mint_or_burn_count") val assetMintOrBurnCount: Int,
    @SerialName("redeemer_count") val redeemerCount: Int,
    @SerialName("valid_contract") val validContract: Boolean,
)

/**
 * Output Amount
 *
 * @property unit The unit of the value
 * @property quantity The quantity of the unit
 */
@Serializable
data class OutputAmount(
    val unit: String,
    val quantity: @Contextual BigInteger,
)

/**
 * Transaction Input
 *
 * @property address Input address
 * @property txHash Hash of the UTXO transaction
 * @property outputIndex UTXO index in the transaction
 * @property collateral Whether the input is a collateral consumed on script validation failure
 * @property reference Whether the input is a reference transaction input
 */
@Serializable
data class TransactionInput(
    val address: String,
    @SerialName("tx_hash") val txHash: String,
    @SerialName("output_index") val outputIndex: Int,
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
    val amount: List<OutputAmount>,
    @SerialName("output_index") val outputIndex: Int,
    @SerialName("data_hash") val dataHash: String?,
    @SerialName("inline_datum") val inlineDatum: String?,
    val collateral: Boolean,
    @SerialName("reference_script_hash") val referenceScriptHash: String?,
)

/**
 * Redeemer
 *
 * @property txIndex Index of the redeemer within the transaction
 * @property purpose Validation purpose
 * @property unitMem The budget in Memory to run a script
 * @property unitSteps The budget in CPU steps to run a script
 */
@Serializable
data class Redeemer(
    @SerialName("tx_index") val txIndex: Int,
    val purpose: String,
    @SerialName("unit_mem") val unitMem: @Contextual BigInteger,
    @SerialName("unit_steps") val unitSteps: @Contextual BigInteger,
)
