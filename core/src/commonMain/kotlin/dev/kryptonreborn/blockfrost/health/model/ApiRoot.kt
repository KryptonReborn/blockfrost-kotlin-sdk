package dev.kryptonreborn.blockfrost.health.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiRoot(
    val url: String? = null,
    val version: String? = null,
)
