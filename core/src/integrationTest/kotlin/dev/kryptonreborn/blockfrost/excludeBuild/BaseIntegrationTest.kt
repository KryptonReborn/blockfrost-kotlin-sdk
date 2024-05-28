package dev.kryptonreborn.blockfrost.excludeBuild

import dev.kryptonreborn.blockfrost.BlockfrostConfig
import org.koin.test.KoinTest
import kotlin.test.BeforeTest

abstract class BaseIntegrationTest : KoinTest {
    @BeforeTest
    fun setup() {
        BlockFrostKotlinSdk.initConfig(
            BlockfrostConfig(
                projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ",
            ),
        )
    }
}
