package dev.kryptonreborn.blockfrost.unittest.metadata

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metadata.CardanoMetadataApi
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContent
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataContentCBOR
import dev.kryptonreborn.blockfrost.metadata.model.TransactionMetadataLabel
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoMetadataApiTest {
    private val queryParameters = QueryParameters()
    private val anyString = "anyString"

    @Test
    fun testGetTransactionMetadataReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_metadata_label.json"
            val expectedData =
                Ktor.json.decodeFromString<List<TransactionMetadataLabel>>(Resource(resource).readText())
            val api =
                createCardanoMetadataApi(
                    resource,
                    CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_LABELS,
                )
            val result = api.getTransactionMetadataLabels(queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionMetadataReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_LABELS,
                HttpStatusCode.OK,
            ) { it.getTransactionMetadataLabels(queryParameters) }
        }

    @Test
    fun testGetTransactionMetadataReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_LABELS,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionMetadataLabels(queryParameters) }
        }

    @Test
    fun testGetTransactionMetadataContentReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_metadata_content.json"
            val expectedData =
                Ktor.json.decodeFromString<List<TransactionMetadataContent>>(Resource(resource).readText())
            val api =
                createCardanoMetadataApi(
                    resource,
                    CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_CONTENTS.replace(
                        ":label",
                        anyString,
                    ),
                )
            val result = api.getTransactionMetadataContents(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionMetadataContentReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_CONTENTS.replace(
                    ":label",
                    anyString,
                ),
                HttpStatusCode.OK,
            ) { it.getTransactionMetadataContents(anyString, queryParameters) }
        }

    @Test
    fun testGetTransactionMetadataContentReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_CONTENTS.replace(
                    ":label",
                    anyString,
                ),
                HttpStatusCode.BadRequest,
            ) { it.getTransactionMetadataContents(anyString, queryParameters) }
        }

    @Test
    fun testGetTransactionMetadataContentCBORReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_metadata_content_cbor.json"
            val expectedData =
                Ktor.json.decodeFromString<List<TransactionMetadataContentCBOR>>(Resource(resource).readText())
            val api =
                createCardanoMetadataApi(
                    resource,
                    CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_LABEL_CBOR.replace(
                        ":label",
                        anyString,
                    ),
                )
            val result = api.getTransactionMetadataContentCBOR(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionMetadataContentCBORReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_LABEL_CBOR.replace(
                    ":label",
                    anyString,
                ),
                HttpStatusCode.OK,
            ) { it.getTransactionMetadataContentCBOR(anyString, queryParameters) }
        }

    @Test
    fun testGetTransactionMetadataContentCBORReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoMetadataApi.PATH_GET_TRANSACTION_METADATA_LABEL_CBOR.replace(
                    ":label",
                    anyString,
                ),
                HttpStatusCode.BadRequest,
            ) { it.getTransactionMetadataContentCBOR(anyString, queryParameters) }
        }

    private fun createCardanoMetadataApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoMetadataApi(
        TestKtorClient.createMockHttpClient(
            path,
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoMetadataApi) -> Unit,
    ) {
        val api =
            createCardanoMetadataApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
