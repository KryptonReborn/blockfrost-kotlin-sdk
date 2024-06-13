package dev.kryptonreborn.blockfrost.assets.model

import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    val decimals: Int?,
    val description: String,
    val logo: String?,
    val name: String,
    val ticker: String?,
    val url: String?,
)
