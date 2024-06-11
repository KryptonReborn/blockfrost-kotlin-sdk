package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpecificAddress(
    val address: String,
    val amount: List<Amount>,
    val script: Boolean,
    @SerialName(value = "stake_address") val stakeAddress: String,
    val type: String,
)
