package dev.kryptonreborn.blockfrost.metrics.model

import kotlinx.serialization.Serializable

/**
 * The `Metric` class represents a usage metric for a specific day.
 *
 * @property time Starting time of the call count interval (ends midnight UTC) in UNIX time.
 * @property calls Sum of all calls for a particular day.
 */
@Serializable
data class Metric(
    /**
     * Starting time of the call count interval (ends midnight UTC) in UNIX time.
     */
    val time: Long,
    /**
     * Sum of all calls for a particular day.
     */
    val calls: Long,
)
