package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
