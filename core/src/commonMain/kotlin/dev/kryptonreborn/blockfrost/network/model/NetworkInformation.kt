package dev.kryptonreborn.blockfrost.network.model

import kotlinx.serialization.Serializable

/**
 * Network Information
 *
 * @property supply Supply information
 * @property stake Stake information
 */
@Serializable
data class NetworkInformation(
    val supply: Supply,
    val stake: Stake,
)

/**
 * Supply Information
 *
 * @property max Maximum supply in Lovelaces
 * @property total Current total (max supply - reserves) supply in Lovelaces
 * @property circulating Current circulating (UTXOs + withdrawables) supply in Lovelaces
 * @property locked Current supply locked by scripts in Lovelaces
 * @property treasury Current supply locked in treasury
 * @property reserves Current supply locked in reserves
 */
@Serializable
data class Supply(
    val max: String,
    val total: String,
    val circulating: String,
    val locked: String,
    val treasury: String,
    val reserves: String,
)

/**
 * Stake Information
 *
 * @property live Current live stake in Lovelaces
 * @property active Current active stake in Lovelaces
 */
@Serializable
data class Stake(
    val live: String,
    val active: String,
)
