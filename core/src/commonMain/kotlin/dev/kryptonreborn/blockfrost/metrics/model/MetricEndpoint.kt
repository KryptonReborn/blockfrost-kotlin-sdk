package dev.kryptonreborn.blockfrost.metrics.model

import kotlinx.serialization.Serializable

/**
 * The `MetricEndpoint` class represents a usage metric for a specific endpoint on a specific day.
 *
 * @property time Starting time of the call count interval (ends midnight UTC) in UNIX time.
 * @property calls Sum of all calls for a particular day and endpoint.
 * @property endpoint Endpoint parent name.
 */
@Serializable
data class MetricEndpoint(
    /**
     * Starting time of the call count interval (ends midnight UTC) in UNIX time.
     */
    val time: Long,
    /**
     * Sum of all calls for a particular day and endpoint.
     */
    val calls: Long,
    /**
     * Endpoint parent name.
     */
    val endpoint: String,
)
