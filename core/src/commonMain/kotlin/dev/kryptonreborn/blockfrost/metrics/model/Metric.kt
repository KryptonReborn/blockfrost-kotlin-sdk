package dev.kryptonreborn.blockfrost.metrics.model

import kotlinx.serialization.Serializable

@Serializable
data class Metric(
    val time: Long,
    val calls: Long,
)
