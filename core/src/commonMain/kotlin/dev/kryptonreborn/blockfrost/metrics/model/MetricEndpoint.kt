package dev.kryptonreborn.blockfrost.metrics.model

import kotlinx.serialization.Serializable

@Serializable
data class MetricEndpoint(
    val time: Long,
    val calls: Long,
    val endpoint: String,
)
