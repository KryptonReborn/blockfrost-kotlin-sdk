package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.BlockFrostClient
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.NetworkType
import dev.kryptonreborn.blockfrost.buildKonfig.BuildKonfig
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest

open class BaseIntegrationTest {
    lateinit var blockfrostClient: BlockFrostClient
    private val projectId: String = BuildKonfig.PROJECT_ID

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
            if (BuildKonfig.DISABLE_INTEGRATION_TESTS || !isRealProjectId(projectId)) return@runTest
            block()
        }

    private fun isRealProjectId(projectId: String) = projectId != "<your project id>"
}
