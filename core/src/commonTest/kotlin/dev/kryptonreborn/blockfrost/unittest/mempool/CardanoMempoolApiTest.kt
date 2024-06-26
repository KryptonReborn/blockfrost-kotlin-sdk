package dev.kryptonreborn.blockfrost.unittest.mempool

import com.goncalossilva.resources.Resource
import com.ionspin.kotlin.bignum.integer.BigInteger
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.mempool.CardanoMempoolApi
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoMempoolApiTest {
    private val anyString = "anyString"

    @Test
    fun testGetMempoolReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/mempool_transactions.json"
            val api = createCardanoMempoolApi(resource, CardanoMempoolApi.PATH_GET_MEMPOOL)
            val result = api.getMempool(QueryParameters())
            assertEquals(1, result.size)
            assertEquals(
                "1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dce628516157f0",
                result.first().txHash,
            )
        }

    @Test
    fun testGetMempoolReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoMempoolApi.PATH_GET_MEMPOOL,
                HttpStatusCode.OK,
            ) { it.getMempool(QueryParameters()) }
        }

    @Test
    fun testGetMempoolReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoMempoolApi.PATH_GET_MEMPOOL,
                HttpStatusCode.BadRequest,
            ) { it.getMempool(QueryParameters()) }
        }

    @Test
    fun testGetMempoolDetailsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/mempool_transaction_details.json"
            val api =
                createCardanoMempoolApi(
                    resource,
                    CardanoMempoolApi.PATH_GET_MEMPOOL_DETAIL.replace(
                        ":hash",
                        anyString,
                    ),
                )
            val result =
                api.getMempoolDetails(anyString)
            assertEquals(
                "8f9a160aac6a32b12b1e71eb84f0455a94a01e99d0b3ef610b48d85f400d7d47",
                result.tx.hash,
            )
            assertEquals(
                BigInteger.parseString("3758528"),
                result.tx.outputAmount[0].quantity,
            )
            assertEquals("lovelace", result.tx.outputAmount[0].unit)
            assertEquals(BigInteger.parseString("171482"), result.tx.outputAmount[1].quantity)
            assertEquals(
                "c881c20e49dbaca3ff6cef365969354150983230c39520b917f5cf7c4e696b65",
                result.tx.outputAmount[1].unit,
            )
            assertEquals(BigInteger(241472), result.tx.fees)
            assertEquals(BigInteger(0), result.tx.deposit)
            assertEquals(1186, result.tx.size)
            assertEquals(127309303L, result.tx.invalidHereafter)
            assertEquals(2, result.tx.utxoCount)
            assertEquals(0, result.tx.withdrawalCount)
            assertEquals(0, result.tx.mirCertCount)
            assertEquals(0, result.tx.delegationCount)
            assertEquals(0, result.tx.stakeCertCount)
            assertEquals(0, result.tx.poolUpdateCount)
            assertEquals(0, result.tx.poolRetireCount)
            assertEquals(0, result.tx.assetMintOrBurnCount)
            assertEquals(1, result.tx.redeemerCount)
            assertTrue(result.tx.validContract)
        }

    @Test
    fun testGetMempoolDetailsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoMempoolApi.PATH_GET_MEMPOOL_DETAIL.replace(
                    ":hash",
                    anyString,
                ),
                HttpStatusCode.OK,
            ) { it.getMempoolDetails(anyString) }
        }

    @Test
    fun testGetMempoolDetailsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoMempoolApi.PATH_GET_MEMPOOL_DETAIL.replace(
                    ":hash",
                    anyString,
                ),
                HttpStatusCode.BadRequest,
            ) { it.getMempoolDetails(anyString) }
        }

    @Test
    fun testGetMempoolByAddressReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/mempool_transactions.json"
            val api =
                createCardanoMempoolApi(
                    resource,
                    CardanoMempoolApi.PATH_GET_MEMPOOL_BY_ADDRESS.replace(
                        ":address",
                        anyString,
                    ),
                )
            val result =
                api.getMempoolByAddress(anyString, QueryParameters())
            assertEquals(1, result.size)
            assertEquals(
                "1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dce628516157f0",
                result.first().txHash,
            )
        }

    @Test
    fun testGetMempoolByAddressReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoMempoolApi.PATH_GET_MEMPOOL_BY_ADDRESS.replace(
                    ":address",
                    anyString,
                ),
                HttpStatusCode.OK,
            ) { it.getMempoolByAddress(anyString, QueryParameters()) }
        }

    @Test
    fun testGetMempoolByAddressReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoMempoolApi.PATH_GET_MEMPOOL_BY_ADDRESS.replace(
                    ":address",
                    anyString,
                ),
                HttpStatusCode.BadRequest,
            ) { it.getMempoolByAddress(anyString, QueryParameters()) }
        }

    private fun createCardanoMempoolApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoMempoolApi(
        TestKtorClient.createMockHttpClient(
            path,
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoMempoolApi) -> Unit,
    ) {
        val api =
            createCardanoMempoolApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
