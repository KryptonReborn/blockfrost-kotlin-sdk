package dev.kryptonreborn.blockfrost.epochs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Epoch Protocol Parameters
 *
 * @property epoch Epoch number
 * @property minFeeA The linear factor for the minimum fee calculation for given epoch
 * @property minFeeB The constant factor for the minimum fee calculation
 * @property maxBlockSize Maximum block body size in Bytes
 * @property maxTxSize Maximum transaction size
 * @property maxBlockHeaderSize Maximum block header size
 * @property keyDeposit The amount of a key registration deposit in Lovelaces
 * @property poolDeposit The amount of a pool registration deposit in Lovelaces
 * @property eMax Epoch bound on pool retirement
 * @property nOpt Desired number of pools
 * @property a0 Pool pledge influence
 * @property rho Monetary expansion
 * @property tau Treasury expansion
 * @property decentralisationParam Percentage of blocks produced by federated nodes
 * @property extraEntropy Seed for extra entropy
 * @property protocolMajorVer Accepted protocol major version
 * @property protocolMinorVer Accepted protocol minor version
 * @property minUtxo Minimum UTXO value
 * @property minPoolCost Minimum stake cost forced on the pool
 * @property nonce Epoch number only used once
 * @property costModels Cost models parameters for Plutus Core scripts
 * @property priceMem The per word cost of script memory usage
 * @property priceStep The cost of script execution step usage
 * @property maxTxExMem The maximum number of execution memory allowed to be used in a single transaction
 * @property maxTxExSteps The maximum number of execution steps allowed to be used in a single transaction
 * @property maxBlockExMem The maximum number of execution memory allowed to be used in a single block
 * @property maxBlockExSteps The maximum number of execution steps allowed to be used in a single block
 * @property maxValSize The maximum Val size
 * @property collateralPercent The percentage of the transactions fee which must be provided as collateral when including non-native scripts
 * @property maxCollateralInputs The maximum number of collateral inputs allowed in a transaction
 * @property coinsPerUtxoSize Cost per UTxO word for Alonzo. Cost per UTxO byte for Babbage and later.
 * @property coinsPerUtxoWord Cost per UTxO word for Alonzo. Cost per UTxO byte for Babbage and later.
 */
@Serializable
data class EpochProtocolParameters(
    val epoch: Int,
    @SerialName("min_fee_a") val minFeeA: Int,
    @SerialName("min_fee_b") val minFeeB: Int,
    @SerialName("max_block_size") val maxBlockSize: Int,
    @SerialName("max_tx_size") val maxTxSize: Int,
    @SerialName("max_block_header_size") val maxBlockHeaderSize: Int,
    @SerialName("key_deposit") val keyDeposit: String,
    @SerialName("pool_deposit") val poolDeposit: String,
    @SerialName("e_max") val eMax: Int,
    @SerialName("n_opt") val nOpt: Int,
    val a0: Double,
    val rho: Double,
    val tau: Double,
    @SerialName("decentralisation_param") val decentralisationParam: Double,
    @SerialName("extra_entropy") val extraEntropy: String?,
    @SerialName("protocol_major_ver") val protocolMajorVer: Int,
    @SerialName("protocol_minor_ver") val protocolMinorVer: Int,
    @SerialName("min_utxo") val minUtxo: String,
    @SerialName("min_pool_cost") val minPoolCost: String,
    val nonce: String,
    @SerialName("cost_models") val costModels: Map<String, JsonElement>?,
    @SerialName("price_mem") val priceMem: Double?,
    @SerialName("price_step") val priceStep: Double?,
    @SerialName("max_tx_ex_mem") val maxTxExMem: String?,
    @SerialName("max_tx_ex_steps") val maxTxExSteps: String?,
    @SerialName("max_block_ex_mem") val maxBlockExMem: String?,
    @SerialName("max_block_ex_steps") val maxBlockExSteps: String?,
    @SerialName("max_val_size") val maxValSize: String?,
    @SerialName("collateral_percent") val collateralPercent: Int?,
    @SerialName("max_collateral_inputs") val maxCollateralInputs: Int?,
    @SerialName("coins_per_utxo_size") val coinsPerUtxoSize: String?,
    @SerialName("coins_per_utxo_word") val coinsPerUtxoWord: String? = null,
)
