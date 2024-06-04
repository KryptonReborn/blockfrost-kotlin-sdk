package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountContentTotal(
    @SerialName("stake_address") val stakeAddress: String,
    @SerialName("received_sum") val receivedSum: List<AccountAddressesAsset>,
    @SerialName("sent_sum") val sentSum: List<AccountAddressesAsset>,
    @SerialName("tx_count") val txCount: Int,
)
