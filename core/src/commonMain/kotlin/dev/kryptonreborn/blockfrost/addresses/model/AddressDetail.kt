package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDetail(
    val address: String = "",
    @SerialName("received_sum") val receivedSum: List<ReceivedSum>,
    @SerialName("sent_sum") val sentSum: List<SentSum>,
    @SerialName("tx_count") val txCount: Int = 0,
)
