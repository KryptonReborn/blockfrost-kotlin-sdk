package dev.kryptonreborn.blockfrost.unittest.blocks

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.blocks.CardanoBlocksApi
import dev.kryptonreborn.blockfrost.blocks.model.BlockAddress
import dev.kryptonreborn.blockfrost.blocks.model.BlockContent
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoBlockApiTest {
    private val anyString = "anyString"
    private val anyNumber = 1
    private val queryParameters = QueryParameters()

    @Test
    fun testGetLatestBlockReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_LATEST_BLOCK)
            val response = api.getLatestBlock()
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetLatestBlockReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_LATEST_BLOCK,
                HttpStatusCode.OK,
            ) { it.getLatestBlock() }
        }

    @Test
    fun testGetLatestBlockReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_LATEST_BLOCK,
                HttpStatusCode.BadRequest,
            ) { it.getLatestBlock() }
        }

    @Test
    fun testGetLatestBlockTxsReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_LATEST_BLOCK_TXS)
            val response = api.getLatestBlockTxs(queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetLatestBlockTxsReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_LATEST_BLOCK_TXS,
                HttpStatusCode.OK,
            ) { it.getLatestBlockTxs(queryParameters) }
        }

    @Test
    fun testGetLatestBlockTxsReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_LATEST_BLOCK_TXS,
                HttpStatusCode.BadRequest,
            ) { it.getLatestBlockTxs(queryParameters) }
        }

    @Test
    fun testGetSpecificBlockReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_SPECIFIC_BLOCK)
            val response = api.getSpecificBlock(anyString)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetSpecificBlockReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_SPECIFIC_BLOCK,
                HttpStatusCode.OK,
            ) { it.getSpecificBlock(anyString) }
        }

    @Test
    fun testGetSpecificBlockReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_SPECIFIC_BLOCK,
                HttpStatusCode.BadRequest,
            ) { it.getSpecificBlock(anyString) }
        }

    @Test
    fun testGetNextBlocksReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/list_block.json"
            val expectedData = resource.resourceToExpectedData<List<BlockContent>>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_NEXT_BLOCKS)
            val response = api.getNextBlocks(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetNextBlocksReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_NEXT_BLOCKS,
                HttpStatusCode.OK,
            ) { it.getNextBlocks(anyString, queryParameters) }
        }

    @Test
    fun testGetNextBlocksReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_NEXT_BLOCKS,
                HttpStatusCode.BadRequest,
            ) { it.getNextBlocks(anyString, queryParameters) }
        }

    @Test
    fun testGetPreviousBlocksReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/list_block.json"
            val expectedData = resource.resourceToExpectedData<List<BlockContent>>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_PREVIOUS_BLOCKS)
            val response = api.getPreviousBlocks(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetPreviousBlocksReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_PREVIOUS_BLOCKS,
                HttpStatusCode.OK,
            ) { it.getPreviousBlocks(anyString, queryParameters) }
        }

    @Test
    fun testGetPreviousBlocksReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_PREVIOUS_BLOCKS,
                HttpStatusCode.BadRequest,
            ) { it.getPreviousBlocks(anyString, queryParameters) }
        }

    @Test
    fun testGetBlockInSlotReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_BLOCK_IN_SLOT)
            val response = api.getBlockInSlot(anyNumber)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetBlockInSlotReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_BLOCK_IN_SLOT,
                HttpStatusCode.OK,
            ) { it.getBlockInSlot(anyNumber) }
        }

    @Test
    fun testGetBlockInSlotReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_BLOCK_IN_SLOT,
                HttpStatusCode.BadRequest,
            ) { it.getBlockInSlot(anyNumber) }
        }

    @Test
    fun testGetBlockInSlotInEpochReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/model/block.json"
            val expectedData = resource.resourceToExpectedData<BlockContent>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_BLOCK_IN_SLOT_IN_EPOCH)
            val response = api.getBlockInSlotInEpoch(anyNumber, anyNumber)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetBlockInSlotInEpochReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_BLOCK_IN_SLOT_IN_EPOCH,
                HttpStatusCode.OK,
            ) { it.getBlockInSlotInEpoch(anyNumber, anyNumber) }
        }

    @Test
    fun testGetBlockInSlotInEpochReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_BLOCK_IN_SLOT_IN_EPOCH,
                HttpStatusCode.BadRequest,
            ) { it.getBlockInSlotInEpoch(anyNumber, anyNumber) }
        }

    @Test
    fun testGetBlockTransactionReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_BLOCK_TRANSACTION)
            val response = api.getBlockTransaction(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetBlockTransactionReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_BLOCK_TRANSACTION,
                HttpStatusCode.OK,
            ) { it.getBlockTransaction(anyString, queryParameters) }
        }

    @Test
    fun testGetBlockTransactionReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_BLOCK_TRANSACTION,
                HttpStatusCode.BadRequest,
            ) { it.getBlockTransaction(anyString, queryParameters) }
        }

    @Test
    fun testGetAddressAffectedInSpecificBlockReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/list_block_addresses.json"
            val expectedData = resource.resourceToExpectedData<List<BlockAddress>>()
            val api = createBlockApi(resource, CardanoBlocksApi.PATH_ADDRESS_AFFECTED)
            val response = api.getAddressAffectedInSpecificBlock(anyString)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAddressAffectedInSpecificBlockReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_ADDRESS_AFFECTED,
                HttpStatusCode.OK,
            ) { it.getAddressAffectedInSpecificBlock(anyString) }
        }

    @Test
    fun testGetAddressAffectedInSpecificBlockReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoBlocksApi.PATH_ADDRESS_AFFECTED,
                HttpStatusCode.BadRequest,
            ) { it.getAddressAffectedInSpecificBlock(anyString) }
        }

    private fun createBlockApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoBlocksApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":hash_or_number", anyString)
                .replace(":slot_number", anyNumber.toString())
                .replace(":epoch_number", anyNumber.toString()),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithFailRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoBlocksApi) -> Unit,
    ) {
        val blockApi =
            createBlockApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(blockApi) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
