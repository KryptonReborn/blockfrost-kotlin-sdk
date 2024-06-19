package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import dev.kryptonreborn.blockfrost.utilities.model.TransactionPayload
import dev.kryptonreborn.blockfrost.utilities.model.TxIn
import dev.kryptonreborn.blockfrost.utilities.model.TxOut
import dev.kryptonreborn.blockfrost.utilities.model.TxOutValue
import kotlinx.serialization.json.JsonObject
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

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluation() =
        runIntegrationTest {
            val result =
                blockfrostClient.submitTransactionForExecutionUnitsEvaluation("")
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
                        "string",
                        listOf(
                            listOf(
                                TxIn("string", 0),
                                TxOut(
                                    "string",
                                    TxOutValue(0, emptyMap()),
                                    "string",
                                    emptyMap(),
                                    emptyMap(),
                                ),
                            ),
                        ),
                    ),
                )
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is JsonObject)
        }
}
