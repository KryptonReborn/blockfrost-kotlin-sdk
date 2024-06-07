package dev.kryptonreborn.blockfrost.accounts.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `AccountContentTotal` class represents the total content of an account.
 *
 * @property stakeAddress The stake address of the account.
 * @property receivedSum The sum of received assets of the account.
 * @property sentSum The sum of sent assets of the account.
 * @property txCount The transaction count of the account.
 */
@Serializable
data class AccountContentTotal(
    @SerialName("stake_address") val stakeAddress: String,
    @SerialName("received_sum") val receivedSum: List<AccountAddressesAsset>,
    @SerialName("sent_sum") val sentSum: List<AccountAddressesAsset>,
    @SerialName("tx_count") val txCount: Int,
)
