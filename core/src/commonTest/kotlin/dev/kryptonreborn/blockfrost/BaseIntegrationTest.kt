package dev.kryptonreborn.blockfrost

import BlockFrostClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest

open class BaseIntegrationTest {
    lateinit var blockfrostClient: BlockFrostClient

    @BeforeTest
    fun setup() {
        blockfrostClient =
            BlockFrostClient(
                blockfrostConfig =
                    BlockfrostConfig(
                        projectId = "your project id",
                        networkType = NetworkType.Mainnet,
                    ),
            )
    }

    fun runTestIfNotCI(block: suspend () -> Unit) =
        runTest {
            if (!BuildConfig.IS_CI) {
                block()
            }
        }
}
