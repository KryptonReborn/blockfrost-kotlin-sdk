package dev.kryptonreborn.blockfrost.unittest.utilities

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.utilities.CardanoUtilitiesApi
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoUtilitiesApiTest {
    @Test
    fun testGetDerivedAddressReturn200() =
        runTest {
            val json = "src/commonTest/resources/model/derived_address.json"
            val cardanoUtilitiesApi =
                createCardanoUtilitiesApi(
                    json,
                    CardanoUtilitiesApi.PATH_DERIVE_ADDRESS.replace(
                        ":xpub",
                        "xpub",
                    ).replace(
                        ":role",
                        "0",
                    ).replace(
                        ":index",
                        "0",
                    ),
                )
            val result = cardanoUtilitiesApi.getDerivedAddress("xpub", 0, 0)
            assertEquals(
                "xpub",
                result.xpub,
            )
            assertEquals(0, result.role)
            assertEquals(0, result.index)
            assertEquals(
                "address",
                result.address,
            )
        }

    @Test
    fun testGetDerivedAddressReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoUtilitiesApi.PATH_DERIVE_ADDRESS.replace(
                    ":xpub",
                    "xpub",
                ).replace(
                    ":role",
                    "0",
                ).replace(
                    ":index",
                    "0",
                ),
                HttpStatusCode.OK,
            ) { it.getDerivedAddress("xpub", 0, 0) }
        }

    @Test
    fun testGetDerivedAddressReturn400() =
        runTest {
            testApiWithFailRequest(
                CardanoUtilitiesApi.PATH_DERIVE_ADDRESS.replace(
                    ":xpub",
                    "xpub",
                ).replace(
                    ":role",
                    "0",
                ).replace(
                    ":index",
                    "0",
                ),
                HttpStatusCode.BadRequest,
            ) { it.getDerivedAddress("xpub", 0, 0) }
        }

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluationReturn200() =
        runTest {
            val cardanoUtilitiesApi =
                createCardanoUtilitiesApi(
                    "src/commonTest/resources/model/any.json",
                    CardanoUtilitiesApi.PATH_SUBMIT_TRANSACTION,
                )
            val result = cardanoUtilitiesApi.submitTransactionForExecutionUnitsEvaluation("")
            assertEquals(result.keys.size, 1)
            assertEquals(result.keys.first(), "key")
            assertEquals(result["key"]?.asString(), "anyString")
        }

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluationReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoUtilitiesApi.PATH_SUBMIT_TRANSACTION,
                HttpStatusCode.OK,
            ) { it.submitTransactionForExecutionUnitsEvaluation("") }
        }

    @Test
    fun testSubmitTransactionForExecutionUnitsEvaluationReturn400() =
        runTest {
            testApiWithFailRequest(
                CardanoUtilitiesApi.PATH_SUBMIT_TRANSACTION,
                HttpStatusCode.BadRequest,
            ) { it.submitTransactionForExecutionUnitsEvaluation("") }
        }

    private fun createCardanoUtilitiesApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoUtilitiesApi(
        TestKtorClient.createMockHttpClient(
            path,
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithFailRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoUtilitiesApi) -> Unit,
    ) {
        val blockApi =
            createCardanoUtilitiesApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(blockApi) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}

private fun JsonElement.asString() = Ktor.json.decodeFromJsonElement<String>(this)
