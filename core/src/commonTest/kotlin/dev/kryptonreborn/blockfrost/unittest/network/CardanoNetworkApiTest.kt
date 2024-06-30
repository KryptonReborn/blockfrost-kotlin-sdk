package dev.kryptonreborn.blockfrost.unittest.network

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.network.CardanoNetworkApi
import dev.kryptonreborn.blockfrost.network.model.EraSummary
import dev.kryptonreborn.blockfrost.network.model.NetworkInformation
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoNetworkApiTest {
    @Test
    fun testGetNetworkInformationReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/network_information.json"
            val content = resource.resourceToExpectedData<NetworkInformation>()
            val api = createCardanoNetworkApi(resource, CardanoNetworkApi.GET_NETWORK_INFORMATION)
            val result = api.getNetworkInformation()
            assertEquals(content, result)
        }

    @Test
    fun testGetNetworkInformationReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoNetworkApi.GET_NETWORK_INFORMATION,
                HttpStatusCode.OK,
            ) { it.getNetworkInformation() }
        }

    @Test
    fun testGetNetworkInformationReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoNetworkApi.GET_NETWORK_INFORMATION,
                HttpStatusCode.BadRequest,
            ) { it.getNetworkInformation() }
        }

    @Test
    fun testQueryNetworkInformationReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_era_summary.json"
            val content = resource.resourceToExpectedData<List<EraSummary>>()
            val api = createCardanoNetworkApi(resource, CardanoNetworkApi.QUERY_SUMMARY_BLOCKCHAIN_ERAS)
            val result = api.querySummaryBlockchainEras()
            assertEquals(content, result)
        }

    @Test
    fun testQueryNetworkInformationReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoNetworkApi.QUERY_SUMMARY_BLOCKCHAIN_ERAS,
                HttpStatusCode.OK,
            ) { it.querySummaryBlockchainEras() }
        }

    @Test
    fun testQueryNetworkInformationReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoNetworkApi.QUERY_SUMMARY_BLOCKCHAIN_ERAS,
                HttpStatusCode.BadRequest,
            ) { it.querySummaryBlockchainEras() }
        }

    private fun createCardanoNetworkApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoNetworkApi(
        TestKtorClient.createMockHttpClient(
            path,
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoNetworkApi) -> Unit,
    ) {
        val api =
            createCardanoNetworkApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
