package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 *
 * @property asset Hex-encoded asset full name
 * @property policyId Policy ID of the asset
 * @property assetName Hex-encoded asset name of the asset
 * @property fingerprint CIP14 based user-facing fingerprint
 * @property quantity Current asset quantity
 * @property initialMintTxHash ID of the initial minting transaction
 * @property mintOrBurnCount Count of mint and burn transactions
 * @property onchainMetadata On-chain metadata stored in the minting transaction under label 721, community discussion around the standard ongoing at https://github.com/cardano-foundation/CIPs/pull/85
 * @property metadata
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
    @SerialName("onchain_metadata") var onchainMetadata: Map<String, JsonElement>?,
    var metadata: Metadata?,
)
