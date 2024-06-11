package dev.kryptonreborn.blockfrost.addresses.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AddressDetail` class represents a detailed address information from the Blockfrost API.
 * @property address The address of the detailed information.
 * @property receivedSum The received sum of the detailed information.
 * @property sentSum The sent sum of the detailed information.
 * @property txCount The transaction count of the detailed information.

 */
@Serializable
data class AddressDetail(
    val address: String = "",
    @SerialName("received_sum") val receivedSum: List<ReceivedSum>,
    @SerialName("sent_sum") val sentSum: List<SentSum>,
    @SerialName("tx_count") val txCount: Long,
)
