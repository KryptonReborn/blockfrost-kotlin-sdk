package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @property txHash Hash of the transaction containing the asset action
 * @property action Action executed upon the asset policy
 * @property amount Asset amount of the specific action
 */

@Serializable
data class AssetHistory(
    val action: Action,
    val amount: String,
    @SerialName("tx_hash") val txHash: String,
) {
    @Serializable
    enum class Action {
        @SerialName("minted")
        MINTED,

        @SerialName("burned")
        BURNED,
    }
}
