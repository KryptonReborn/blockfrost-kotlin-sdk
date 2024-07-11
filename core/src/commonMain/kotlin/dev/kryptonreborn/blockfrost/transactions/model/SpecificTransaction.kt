package dev.kryptonreborn.blockfrost.transactions.model

import dev.kryptonreborn.blockfrost.base.model.Amount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Specific Transaction
 *
 * @property hash Transaction hash
 * @property block Block hash
 * @property blockHeight Block number
 * @property blockTime Block creation time in UNIX time
 * @property slot Slot number
 * @property index Transaction index within the block
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
data class SpecificTransaction(
    val hash: String,
    val block: String,
    @SerialName("block_height") val blockHeight: Int,
    @SerialName("block_time") val blockTime: Int,
    val slot: Int,
    val index: Int,
    @SerialName("output_amount") val outputAmount: List<Amount>,
    val fees: String,
    val deposit: String,
    val size: Int,
    @SerialName("invalid_before") val invalidBefore: String?,
    @SerialName("invalid_hereafter") val invalidHereafter: String?,
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
