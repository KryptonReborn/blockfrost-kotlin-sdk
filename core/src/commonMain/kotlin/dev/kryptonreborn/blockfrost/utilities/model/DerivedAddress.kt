package dev.kryptonreborn.blockfrost.utilities.model

import kotlinx.serialization.Serializable

/**
 * Derived Address
 *
 * @property xpub Script hash
 * @property role Account role
 * @property index Address index
 * @property address Derived address
 */
@Serializable
data class DerivedAddress(
    val xpub: String,
    val role: Int,
    val index: Int,
    val address: String,
)
