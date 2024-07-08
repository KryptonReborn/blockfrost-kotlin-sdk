package dev.kryptonreborn.blockfrost.base.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RedeemerPurpose {
    @SerialName("spend")
    SPEND,

    @SerialName("mint")
    MINT,

    @SerialName("cert")
    CERT,

    @SerialName("reward")
    REWARD,
}
