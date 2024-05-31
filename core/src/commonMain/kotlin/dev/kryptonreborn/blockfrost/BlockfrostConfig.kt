package dev.kryptonreborn.blockfrost

import io.ktor.client.plugins.logging.LogLevel

data class BlockfrostConfig(
    val networkType: NetworkType = NetworkType.Mainnet,
    var projectId: String? = null,
    val logLevel: LogLevel = LogLevel.ALL,
)

enum class NetworkType(val url: String) {
    Mainnet("https://cardano-mainnet.blockfrost.io/api/v0"),
    Preprod("https://cardano-preprod.blockfrost.io/api/v0"),
    Preview("https://cardano-preview.blockfrost.io/api/v0"),
}
