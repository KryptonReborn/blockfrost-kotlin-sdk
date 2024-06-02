package dev.kryptonreborn.blockfrost

import BlockFrostClient
import dev.kryptonreborn.blockfrost.buildKonfig.BuildKonfig
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
            if (!BuildKonfig.IS_CI) {
                block()
            }
        }
}
