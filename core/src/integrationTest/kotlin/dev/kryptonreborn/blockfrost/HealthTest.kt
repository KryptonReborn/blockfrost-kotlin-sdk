package dev.kryptonreborn.blockfrost

import LocalOnlyCondition
import io.kotest.core.annotation.EnabledIf
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest

@EnabledIf(LocalOnlyCondition::class)
class HealthTest : FunSpec({

    beforeTest {
        BlockFrostKotlinSdk.initConfig(BlockfrostConfig(projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ"))
    }

    test("getHealth should return a non-null result and isHealthy should be true") {
        runTest {
            val result = BlockFrostKotlinSdk.getHealth()
            result.getOrNull() shouldNotBe null
            result.getOrNull()?.isHealthy shouldBe true
        }
    }

    test("getApiRoot should return a non-null result and url should be 'https://blockfrost.io/'") {
        runTest {
            val result = BlockFrostKotlinSdk.getApiRoot()
            result.getOrNull() shouldNotBe null
            result.getOrNull()?.url shouldBe "https://blockfrost.io/"
        }
    }

    test("getCurrentBackendTime should return a non-null serverTime") {
        runTest {
            val result = BlockFrostKotlinSdk.getCurrentBackendTime()
            result.getOrNull()?.serverTime shouldNotBe null
        }
    }
})
