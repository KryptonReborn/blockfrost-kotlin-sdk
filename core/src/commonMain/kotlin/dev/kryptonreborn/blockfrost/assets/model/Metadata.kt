package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Serializable

/**
 *
 * @property name Asset name
 * @property description Asset description
 * @property ticker
 * @property url Asset website
 * @property logo Base64 encoded logo of the asset
 * @property decimals Number of decimal places of the asset unit
 */
@Serializable
data class Metadata(
    val decimals: Int?,
    val description: String,
    val logo: String?,
    val name: String,
    val ticker: String?,
    val url: String?,
)
