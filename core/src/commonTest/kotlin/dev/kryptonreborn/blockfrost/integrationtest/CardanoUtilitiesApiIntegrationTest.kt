package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import dev.kryptonreborn.blockfrost.utilities.model.TransactionInput
import dev.kryptonreborn.blockfrost.utilities.model.TransactionOutput
import dev.kryptonreborn.blockfrost.utilities.model.TransactionOutputValue
import dev.kryptonreborn.blockfrost.utilities.model.TransactionPayload
import dev.kryptonreborn.blockfrost.utilities.model.UTxO
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoUtilitiesApiIntegrationTest : BaseIntegrationTest() {
    private val xpub =
        "d507c8f866691bd96e131334c355188b1a1d0b2fa0ab11545075aab332d77d9eb19657ad13ee581b56b0f8d744d66ca356b93d42fe176b3de007d53e9c4c4e7a"
    private val cbor =
        "84a500818258205d4e2439244dc018d33e0ccb7521cda8f112273a49aeb6f412675b70d88599" +
            "cd010186825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa0" +
            "8b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a069db9c0825839003dd93bd0a5d1dd87b6413484b63a" +
            "d1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a6" +
            "3339a3a825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08" +
            "b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a319b2c5f825839003dd93bd0a5d1dd87b6413484b63ad" +
            "1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a18" +
            "cd9630825839003dd93bd0a5d1dd87b6413484b63ad1b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b" +
            "2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a18cd9630825839003dd93bd0a5d1dd87b6413484b63ad1" +
            "b03d5e7a87df82395c63b6f8ba39ff259bb19dfa08b2af9cbae0da0e80a2e7e9057c41ccd5c7344db91a004c4b40021a0002be85031a031e00790800a0f5f6"

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
                        uTxO =
                            UTxO(
                                input =
                                    TransactionInput(
                                        "f9f2d84a12a1b0a3d68ef0c04b7d209fb9488b40d796f0c4cecc9155b67189b0",
                                        0,
                                    ),
                                output =
                                    TransactionOutput(
                                        "addr_test1qpfn4z6j0y7kflf6rjnrhfzz27x4mpx39fgt80e08" +
                                            "nlfth8xpvuvkjamflz5fttchsms2jpzpy602l3anedf2fd4n8xqzj4k4d",
                                        value =
                                            TransactionOutputValue(
                                                coins = 10000000000000,
                                                assets = emptyMap(),
                                            ),
                                        datumHash = "68720e1ba020fa18bfeb8862f62465b02d237d56e1de6df69e49f297d1a8afa8",
                                        datum = emptyMap(),
                                        script = emptyMap(),
                                    ),
                            ),
                    ),
                )
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is JsonObject)
        }
}
