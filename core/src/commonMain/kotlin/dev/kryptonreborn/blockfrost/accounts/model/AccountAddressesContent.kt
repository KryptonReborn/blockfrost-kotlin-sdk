package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountAddressesContent(
    @SerialName("address") val address: String,
)
