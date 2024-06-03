package dev.kryptonreborn.blockfrost

import BlockFrostClient
import dev.kryptonreborn.blockfrost.buildKonfig.BuildKonfig
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest

open class BaseIntegrationTest {
    lateinit var blockfrostClient: BlockFrostClient
    private val projectId: String = "<your project id>"

    @BeforeTest
    fun setup() {
        blockfrostClient =
            BlockFrostClient(
                blockfrostConfig =
                    BlockfrostConfig(
                        projectId = projectId,
                        networkType = NetworkType.Mainnet,
                    ),
            )
    }

    fun runIntegrationTest(block: suspend () -> Unit) =
        runTest {
            if (BuildKonfig.IS_CI) return@runTest
            if (!isRealProjectId(projectId)) return@runTest
            block()
        }

    private fun isRealProjectId(projectId: String) = projectId != "<your project id>"
}
