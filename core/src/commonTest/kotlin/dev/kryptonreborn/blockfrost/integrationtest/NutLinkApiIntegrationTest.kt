package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.nutlink.model.NutLinkAddressMetadata
import dev.kryptonreborn.blockfrost.nutlink.model.OracleTicker
import dev.kryptonreborn.blockfrost.nutlink.model.TickerRecord
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class NutLinkApiIntegrationTest : BaseIntegrationTest() {
    private val address =
        "addr1q85yx2w7ragn5sx6umgmtjpc3865s9sg59sz4rrh6f90kgwfwlzu3w8ttacqg89mkdgwshwnplj5c5n9f8dhp0h55q2q7qm63t"

    @Test
    fun testGetNutLink() =
        runIntegrationTest {
            val result = blockfrostClient.getNutLink(address)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is NutLinkAddressMetadata)
        }

    @Test
    fun testGetNutLinkTickers() =
        runIntegrationTest {
            val result = blockfrostClient.getNutLinkTickers(address)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<OracleTicker>)
        }

    @Test
    fun testGetNutLinkSpecificTickerForAddress() =
        runIntegrationTest {
            val result = blockfrostClient.getNutLinkSpecificTickerForAddress(address, "ADABTC")
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TickerRecord>)
        }

    @Test
    fun testGetNutLinkSpecificTicker() =
        runIntegrationTest {
            val result = blockfrostClient.getNutLinkSpecificTicker("ADABTC")
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TickerRecord>)
        }
}
