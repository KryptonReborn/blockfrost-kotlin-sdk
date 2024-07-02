package dev.kryptonreborn.blockfrost.unittest.pool

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.pool.CardanoPoolApi
import dev.kryptonreborn.blockfrost.pool.model.StakePool
import dev.kryptonreborn.blockfrost.pool.model.StakePoolDelegator
import dev.kryptonreborn.blockfrost.pool.model.StakePoolHistory
import dev.kryptonreborn.blockfrost.pool.model.StakePoolInfo
import dev.kryptonreborn.blockfrost.pool.model.StakePoolMetadata
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRelay
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRetire
import dev.kryptonreborn.blockfrost.pool.model.StakePoolUpdate
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoPoolApiTest {
    private val queryParameters = QueryParameters()
    private val anyString = "anyString"

    @Test
    fun testGetListStakePoolsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_STAKE_POOLS)
            val result = api.getListStakePools(queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListStakePoolsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOLS,
                HttpStatusCode.OK,
            ) { it.getListStakePools(queryParameters) }
        }

    @Test
    fun testGetListStakePoolsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOLS,
                HttpStatusCode.BadRequest,
            ) { it.getListStakePools(queryParameters) }
        }

    @Test
    fun testGetListStakePoolsExtendedReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool.json"
            val expectedData = resource.resourceToExpectedData<List<StakePool>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_STAKE_POOLS_EXTENDED)
            val result = api.getListStakePoolsExtended(queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListStakePoolsExtendedReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOLS_EXTENDED,
                HttpStatusCode.OK,
            ) { it.getListStakePoolsExtended(queryParameters) }
        }

    @Test
    fun testGetListStakePoolsExtendedReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOLS_EXTENDED,
                HttpStatusCode.BadRequest,
            ) { it.getListStakePoolsExtended(queryParameters) }
        }

    @Test
    fun testGetListRetiredStakePoolsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_retire.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolRetire>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_RETIRED_STAKE_POOLS)
            val result = api.getListRetiredStakePools(queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListRetiredStakePoolsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_RETIRED_STAKE_POOLS,
                HttpStatusCode.OK,
            ) { it.getListRetiredStakePools(queryParameters) }
        }

    @Test
    fun testGetListRetiredStakePoolsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_RETIRED_STAKE_POOLS,
                HttpStatusCode.BadRequest,
            ) { it.getListRetiredStakePools(queryParameters) }
        }

    @Test
    fun testGetListRetiringStakePoolsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_retire.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolRetire>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_RETIRING_STAKE_POOLS)
            val result = api.getListRetiringStakePools(queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListRetiringStakePoolsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_RETIRING_STAKE_POOLS,
                HttpStatusCode.OK,
            ) { it.getListRetiringStakePools(queryParameters) }
        }

    @Test
    fun testGetListRetiringStakePoolsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_RETIRING_STAKE_POOLS,
                HttpStatusCode.BadRequest,
            ) { it.getListRetiringStakePools(queryParameters) }
        }

    @Test
    fun testGetSpecificStakePoolReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_stake_pool.json"
            val expectedData = resource.resourceToExpectedData<StakePoolInfo>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_SPECIFIC_STAKE_POOL)
            val result = api.getSpecificStakePool(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetSpecificStakePoolReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_SPECIFIC_STAKE_POOL,
                HttpStatusCode.OK,
            ) { it.getSpecificStakePool(anyString) }
        }

    @Test
    fun testGetSpecificStakePoolReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_SPECIFIC_STAKE_POOL,
                HttpStatusCode.BadRequest,
            ) { it.getSpecificStakePool(anyString) }
        }

    @Test
    fun testGetStakePoolHistoryReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_history.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolHistory>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_STAKE_POOL_HISTORY)
            val result = api.getStakePoolHistory(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetStakePoolHistoryReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_STAKE_POOL_HISTORY,
                HttpStatusCode.OK,
            ) { it.getStakePoolHistory(anyString, queryParameters) }
        }

    @Test
    fun testGetStakePoolHistoryReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_STAKE_POOL_HISTORY,
                HttpStatusCode.BadRequest,
            ) { it.getStakePoolHistory(anyString, queryParameters) }
        }

    @Test
    fun testGetStakePoolMetadataReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/stake_pool_metadata.json"
            val expectedData = resource.resourceToExpectedData<StakePoolMetadata>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_STAKE_POOL_METADATA)
            val result = api.getStakePoolMetadata(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetStakePoolMetadataReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_STAKE_POOL_METADATA,
                HttpStatusCode.OK,
            ) { it.getStakePoolMetadata(anyString) }
        }

    @Test
    fun testGetStakePoolMetadataReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_STAKE_POOL_METADATA,
                HttpStatusCode.BadRequest,
            ) { it.getStakePoolMetadata(anyString) }
        }

    @Test
    fun testGetStakePoolRelaysReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_relays.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolRelay>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_STAKE_POOL_RELAYS)
            val result = api.getStakePoolRelays(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetStakePoolRelaysReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_STAKE_POOL_RELAYS,
                HttpStatusCode.OK,
            ) { it.getStakePoolRelays(anyString) }
        }

    @Test
    fun testGetStakePoolRelaysReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_STAKE_POOL_RELAYS,
                HttpStatusCode.BadRequest,
            ) { it.getStakePoolRelays(anyString) }
        }

    @Test
    fun testGetListStakePoolDelegatorsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_delegator.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolDelegator>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_STAKE_POOL_DELEGATORS)
            val result = api.getListStakePoolDelegators(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListStakePoolDelegatorsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOL_DELEGATORS,
                HttpStatusCode.OK,
            ) { it.getListStakePoolDelegators(anyString, queryParameters) }
        }

    @Test
    fun testGetListStakePoolDelegatorsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOL_DELEGATORS,
                HttpStatusCode.BadRequest,
            ) { it.getListStakePoolDelegators(anyString, queryParameters) }
        }

    @Test
    fun testGetListStakePoolBlocksReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_STAKE_POOL_BLOCKS)
            val result = api.getListStakePoolBlocks(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListStakePoolBlocksReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOL_BLOCKS,
                HttpStatusCode.OK,
            ) { it.getListStakePoolBlocks(anyString, queryParameters) }
        }

    @Test
    fun testGetListStakePoolBlocksReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOL_BLOCKS,
                HttpStatusCode.BadRequest,
            ) { it.getListStakePoolBlocks(anyString, queryParameters) }
        }

    @Test
    fun testGetListStakePoolUpdatesReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_update.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolUpdate>>()
            val api = createCardanoPoolApi(resource, CardanoPoolApi.PATH_LIST_STAKE_POOL_UPDATES)
            val result = api.getListStakePoolUpdates(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetListStakePoolUpdatesReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOL_UPDATES,
                HttpStatusCode.OK,
            ) { it.getListStakePoolUpdates(anyString, queryParameters) }
        }

    @Test
    fun testGetListStakePoolUpdatesReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoPoolApi.PATH_LIST_STAKE_POOL_UPDATES,
                HttpStatusCode.BadRequest,
            ) { it.getListStakePoolUpdates(anyString, queryParameters) }
        }

    private fun createCardanoPoolApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoPoolApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":pool_id", anyString),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoPoolApi) -> Unit,
    ) {
        val api =
            createCardanoPoolApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
