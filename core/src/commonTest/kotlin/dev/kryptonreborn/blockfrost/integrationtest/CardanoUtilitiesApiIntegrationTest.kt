package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import dev.kryptonreborn.blockfrost.utilities.model.TransactionPayload
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoUtilitiesApiIntegrationTest : BaseIntegrationTest() {
    private val xpub =
        "d507c8f866691bd96e131334c355188b1a1d0b2fa0ab11545075aab332d77d9eb19657ad13ee581b56b0f8d744d66ca356b93d42fe176b3de007d53e9c4c4e7a"
    private val cbor="84a500818258205d4e2439244dc018d33e0ccb7521cda8f112273a49aeb6f412675b70d88599cd010186825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a069db9c0825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a63339a3a825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a319b2c5f825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a18cd9630825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a18cd9630825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a004c4b40021a0002be85031a031e00790800a0f5f6"
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

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluation() =
        runIntegrationTest {
            val result =
                blockfrostClient.submitTransactionForExecutionUnitsEvaluation(cbor)
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is JsonObject)
        }

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluationWithUtxos() =
        runIntegrationTest {
            val result =
                blockfrostClient.submitTransactionForExecutionUnitsEvaluationWithUtxos(
                    TransactionPayload(
                        cbor,
                    ),
                )
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is JsonObject)
        }
}
