package dev.kryptonreborn.blockfrost.unittest.assets

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.assets.CardanoAssetsApi
import dev.kryptonreborn.blockfrost.assets.model.Asset
import dev.kryptonreborn.blockfrost.assets.model.AssetAddress
import dev.kryptonreborn.blockfrost.assets.model.AssetHistory
import dev.kryptonreborn.blockfrost.assets.model.AssetTransaction
import dev.kryptonreborn.blockfrost.assets.model.SpecificAsset
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoAssetsApiTest {
    private val anyString = "anyString"
    private val queryParameters = QueryParameters()

    @Test
    fun testGetAssetsReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/api_assets_200.json"
            val expectedData = resource.resourceToExpectedData<List<Asset>>()
            val api = createAssetsApi(resource, CardanoAssetsApi.PATH_ASSETS)
            val response = api.getAssets(queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAssetsReturn200EWithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSETS,
                HttpStatusCode.OK,
            ) { it.getAssets(queryParameters) }
        }

    @Test
    fun testGetAssetsReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSETS,
                HttpStatusCode.BadRequest,
            ) { it.getAssets(queryParameters) }
        }

    @Test
    fun testGetSpecificAssetReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_asset.json"
            val expectedData = resource.resourceToExpectedData<SpecificAsset>()
            val api = createAssetsApi(resource, CardanoAssetsApi.PATH_SPECIFIC_ASSET)
            val response = api.getSpecificAsset(anyString)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetSpecificAssetReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_SPECIFIC_ASSET,
                HttpStatusCode.OK,
            ) { it.getSpecificAsset(anyString) }
        }

    @Test
    fun testGetSpecificAssetReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_SPECIFIC_ASSET,
                HttpStatusCode.BadRequest,
            ) { it.getSpecificAsset(anyString) }
        }

    @Test
    fun testGetAssetHistoryReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/api_asset_histories_200.json"
            val expectedData = resource.resourceToExpectedData<List<AssetHistory>>()
            val api = createAssetsApi(resource, CardanoAssetsApi.PATH_ASSET_HISTORY)
            val response = api.getAssetHistory(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAssetHistoryReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_HISTORY,
                HttpStatusCode.OK,
            ) { it.getAssetHistory(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetHistoryReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_HISTORY,
                HttpStatusCode.BadRequest,
            ) { it.getAssetHistory(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetTxsReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/list_string.json"
            val expectedData = resource.resourceToExpectedData<List<String>>()
            val api = createAssetsApi(resource, CardanoAssetsApi.PATH_ASSET_TXS)
            val response = api.getAssetTxs(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAssetTxsReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_TXS,
                HttpStatusCode.OK,
            ) { it.getAssetTxs(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetTxsReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_TXS,
                HttpStatusCode.BadRequest,
            ) { it.getAssetTxs(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetTransactionsReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/api_asset_transactions_200.json"
            val expectedData = resource.resourceToExpectedData<List<AssetTransaction>>()
            val api = createAssetsApi(resource, CardanoAssetsApi.PATH_ASSET_TRANSACTION)
            val response = api.getAssetTransactions(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAssetTransactionsReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_TRANSACTION,
                HttpStatusCode.OK,
            ) { it.getAssetTransactions(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetTransactionsReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_TRANSACTION,
                HttpStatusCode.BadRequest,
            ) { it.getAssetTransactions(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetAddressesReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/api_asset_addresses_200.json"
            val expectedData = resource.resourceToExpectedData<List<AssetAddress>>()
            val api = createAssetsApi(resource, CardanoAssetsApi.PATH_ASSET_ADDRESSES)
            val response = api.getAssetAddresses(anyString, queryParameters)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAssetAddressesReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_ADDRESSES,
                HttpStatusCode.OK,
            ) { it.getAssetAddresses(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetAddressesReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_ADDRESSES,
                HttpStatusCode.BadRequest,
            ) { it.getAssetAddresses(anyString, queryParameters) }
        }

    @Test
    fun testGetAssetPolicyReturn200Correct() =
        runTest {
            val resource = "src/commonTest/resources/api_assets_200.json"
            val expectedData = resource.resourceToExpectedData<List<Asset>>()
            val api =
                createAssetsApi(
                    resource,
                    CardanoAssetsApi.PATH_ASSET_POLICY.replace(":policy_id", anyString),
                )
            val response = api.getAssetPolicy(anyString)
            assertEquals(expectedData, response)
        }

    @Test
    fun testGetAssetPolicyReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_POLICY.replace(":policy_id", anyString),
                HttpStatusCode.OK,
            ) { it.getAssetPolicy(anyString) }
        }

    @Test
    fun testGetAssetPolicyReturn400Error() =
        runTest {
            testApiWithFailRequest(
                CardanoAssetsApi.PATH_ASSET_POLICY.replace(":policy_id", anyString),
                HttpStatusCode.BadRequest,
            ) { it.getAssetPolicy(anyString) }
        }

    private fun createAssetsApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoAssetsApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":asset", anyString),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithFailRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoAssetsApi) -> Unit,
    ) {
        val accountsApi =
            createAssetsApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(accountsApi) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
