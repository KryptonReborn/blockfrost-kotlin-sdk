package dev.kryptonreborn.blockfrost

import io.ktor.client.plugins.logging.LogLevel

/**
 * The `BlockfrostConfig` class represents the configuration for the Blockfrost API client.
 *
 * @property networkType The network type for the Blockfrost API client.
 * @property projectId The project ID for the Blockfrost API client.
 * @property logLevel The log level for the Blockfrost API client.
 */
data class BlockfrostConfig(
    val networkType: NetworkType = NetworkType.Mainnet,
    var projectId: String? = null,
    val logLevel: BlockfrostLogLevel = BlockfrostLogLevel.NONE,
)

/**
 * The `NetworkType` enum represents the network type for the Blockfrost API client.
 *
 * @property url The base URL of the network type.
 */
enum class NetworkType(val url: String) {
    /**
     * Mainnet network type.
     *
     * The main production network for Cardano.
     */
    Mainnet("https://cardano-mainnet.blockfrost.io"),

    /**
     * Preprod network type.
     *
     * The pre-production network for testing before deployment to Mainnet.
     */
    Preprod("https://cardano-preprod.blockfrost.io"),

    /**
     * Preview network type.
     *
     * @property url The base URL for the Preview network.
     */
    Preview("https://cardano-preview.blockfrost.io"),
}

/**
 * The `BlockfrostLogLevel` enum represents the log level for the Blockfrost API client.
 *
 * @property value The log level value.
 */
enum class BlockfrostLogLevel(val value: LogLevel) {
    /**
     * Log all HTTP requests and responses.
     */
    ALL(LogLevel.ALL),

    /**
     * Log HTTP headers.
     */
    HEADERS(LogLevel.HEADERS),

    /**
     * Log HTTP body.
     */
    BODY(LogLevel.BODY),

    /**
     * Log basic information.
     */
    INFO(LogLevel.INFO),

    /**
     * No logging.
     */
    NONE(LogLevel.NONE),
}
