package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalAddresses(
    @SerialName("stake_address") val stakeAddress: String,
    @SerialName("received_sum") val receivedSum: List<AssociatedAsset>,
    @SerialName("sent_sum") val sentSum: List<AssociatedAsset>,
    @SerialName("tx_count") val txCount: Int,
)
