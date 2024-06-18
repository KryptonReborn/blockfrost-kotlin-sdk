package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoUtilitiesApiIntegrationTest : BaseIntegrationTest() {
    private val xpub =
        "d507c8f866691bd96e131334c355188b1a1d0b2fa0ab11545075aab332d77d9eb19657ad13ee581b56b0f8d744d66ca356b93d42fe176b3de007d53e9c4c4e7a"

    @Test
    fun testGetDerivedAddress() =
        runIntegrationTest {
            val result =
                blockfrostClient.getDerivedAddress(
                    xpub,
                    0,
                    0,
                )
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is DerivedAddress)
        }
}
