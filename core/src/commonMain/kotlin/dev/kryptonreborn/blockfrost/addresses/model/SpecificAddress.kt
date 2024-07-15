package dev.kryptonreborn.blockfrost.addresses.model

import dev.kryptonreborn.blockfrost.base.model.Amount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The `SpecificAddress` class represents a specific address from the Blockfrost API.
 * @property address The address of the specific address.
 * @property amount The amount of the specific address.
 * @property script The script of the specific address.
 * @property stakeAddress The stake address of the specific address.
 * @property type The type of the specific address.
 */
@Serializable
data class SpecificAddress(
    val address: String,
    val amount: List<Amount>,
    val script: Boolean,
    @SerialName(value = "stake_address") val stakeAddress: String,
    val type: String,
)
