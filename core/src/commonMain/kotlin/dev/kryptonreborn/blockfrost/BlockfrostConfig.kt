package dev.kryptonreborn.blockfrost

import io.ktor.client.plugins.logging.LogLevel

data class BlockfrostConfig(
    val networkType: NetworkType = NetworkType.Mainnet,
    var projectId: String? = null,
    val logLevel: BlockfrostLogLevel = BlockfrostLogLevel.NONE,
)

enum class NetworkType(val url: String) {
    Mainnet("https://cardano-mainnet.blockfrost.io/api/v0"),
    Preprod("https://cardano-preprod.blockfrost.io/api/v0"),
    Preview("https://cardano-preview.blockfrost.io/api/v0"),
}

enum class BlockfrostLogLevel(val value: LogLevel) {
    ALL(LogLevel.ALL),
    HEADERS(LogLevel.HEADERS),
    BODY(LogLevel.BODY),
    INFO(LogLevel.INFO),
    NONE(LogLevel.NONE),
}