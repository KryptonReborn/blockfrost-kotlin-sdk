package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param asset Hex-encoded asset full name
 * @param policyId Policy ID of the asset
 * @param assetName Hex-encoded asset name of the asset
 * @param fingerprint CIP14 based user-facing fingerprint
 * @param quantity Current asset quantity
 * @param initialMintTxHash ID of the initial minting transaction
 * @param mintOrBurnCount Count of mint and burn transactions
 * @param onchainMetadata On-chain metadata stored in the minting transaction under label 721, community discussion around the standard ongoing at https://github.com/cardano-foundation/CIPs/pull/85
 * @param metadata
 */

@Serializable
data class SpecificAsset(
    @SerialName("asset") var asset: String,
    @SerialName("policy_id") var policyId: String,
    @SerialName("asset_name") var assetName: String?,
    @SerialName("fingerprint") var fingerprint: String,
    @SerialName("quantity") var quantity: String,
    @SerialName("initial_mint_tx_hash") var initialMintTxHash: String,
    @SerialName("mint_or_burn_count") var mintOrBurnCount: Int,
    @SerialName("onchain_metadata") var onchainMetadata: Map<String, @Contextual Any>?,
    var metadata: Metadata?,
)
