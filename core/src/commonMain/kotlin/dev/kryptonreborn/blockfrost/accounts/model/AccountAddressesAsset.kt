package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountAddressesAsset` class represents an asset associated with an account address.
 *
 * @property unit The unit of the asset.
 * @property quantity The quantity of the asset.
 */
@Serializable
data class AccountAddressesAsset(
    @SerialName("unit") val unit: String,
    @SerialName("quantity") val quantity: String,
)
