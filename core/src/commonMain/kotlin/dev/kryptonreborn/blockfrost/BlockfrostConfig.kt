package dev.kryptonreborn.blockfrost

data class BlockfrostConfig(
    val networkType: NetworkType = NetworkType.Mainnet,
    var projectId: String? = null,
)

enum class NetworkType(val url: String) {
    Mainnet("https://cardano-mainnet.blockfrost.io/api/v0"),
    Preprod("https://cardano-preprod.blockfrost.io/api/v0"),
    Preview("https://cardano-preview.blockfrost.io/api/v0"),
}
