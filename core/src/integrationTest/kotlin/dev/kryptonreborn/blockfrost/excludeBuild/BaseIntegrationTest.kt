package dev.kryptonreborn.blockfrost.excludeBuild

import dev.kryptonreborn.blockfrost.di.coreModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseIntegrationTest : KoinTest {
    @BeforeTest
    fun setup() {
        BlockFrostKotlinSdk.initConfig("mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ")
        startKoin {
            modules(coreModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}
