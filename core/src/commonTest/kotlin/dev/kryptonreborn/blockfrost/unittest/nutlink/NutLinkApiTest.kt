package dev.kryptonreborn.blockfrost.unittest.nutlink

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.nutlink.NutLinkApi
import dev.kryptonreborn.blockfrost.nutlink.NutLinkApi.Companion.PATH_GET_NUTLINK
import dev.kryptonreborn.blockfrost.nutlink.NutLinkApi.Companion.PATH_GET_NUTLINK_TICKERS
import dev.kryptonreborn.blockfrost.nutlink.model.NutLinkAddressMetadata
import dev.kryptonreborn.blockfrost.nutlink.model.OracleTicker
import dev.kryptonreborn.blockfrost.nutlink.model.TickerRecord
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class NutLinkApiTest {
    @Test
    fun testGetAddressMetadataReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/nutlink_address_metadata.json"
            val content = resource.resourceToExpectedData<NutLinkAddressMetadata>()
            val api = createNutlinkApi(resource, PATH_GET_NUTLINK)
            val result = api.getNutLink("address")
            assertEquals(content, result)
        }

    @Test
    fun testGetAddressMetadataReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_NUTLINK,
                HttpStatusCode.OK,
            ) { it.getNutLink("address") }
        }

    @Test
    fun testGetAddressMetadataReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_NUTLINK,
                HttpStatusCode.BadRequest,
            ) { it.getNutLink("address") }
        }

    @Test
    fun testGetNutLinkTickersReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_oracle_ticker.json"
            val content = resource.resourceToExpectedData<List<OracleTicker>>()
            val api = createNutlinkApi(resource, PATH_GET_NUTLINK_TICKERS)
            val result = api.getNutLinkTickers("address", QueryParameters())
            assertEquals(content, result)
        }

    @Test
    fun testGetNutLinkTickersReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_NUTLINK_TICKERS,
                HttpStatusCode.OK,
            ) { it.getNutLinkTickers("address", QueryParameters()) }
        }

    @Test
    fun testGetNutLinkTickersReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_NUTLINK_TICKERS,
                HttpStatusCode.BadRequest,
            ) { it.getNutLinkTickers("address", QueryParameters()) }
        }

    @Test
    fun testGetNutLinkSpecificTickerForAddressReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_ticker_record.json"
            val content = resource.resourceToExpectedData<List<TickerRecord>>()
            val api = createNutlinkApi(resource, NutLinkApi.PATH_GET_NUTLINK_SPECIFIC_TICKER_FOR_ADDRESS)
            val result = api.getNutLinkSpecificTickerForAddress("address", "ticker", QueryParameters())
            assertEquals(content, result)
        }

    @Test
    fun testGetNutLinkSpecificTickerForAddressReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                NutLinkApi.PATH_GET_NUTLINK_SPECIFIC_TICKER_FOR_ADDRESS,
                HttpStatusCode.OK,
            ) { it.getNutLinkSpecificTickerForAddress("address", "ticker", QueryParameters()) }
        }

    @Test
    fun testGetNutLinkSpecificTickerForAddressReturn400() =
        runTest {
            testApiWithBadRequest(
                NutLinkApi.PATH_GET_NUTLINK_SPECIFIC_TICKER_FOR_ADDRESS,
                HttpStatusCode.BadRequest,
            ) { it.getNutLinkSpecificTickerForAddress("address", "ticker", QueryParameters()) }
        }

    @Test
    fun testGetNutLinkSpecificTickerReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_ticker_record.json"
            val content = resource.resourceToExpectedData<List<TickerRecord>>()
            val api = createNutlinkApi(resource, NutLinkApi.PATH_GET_NUTLINK_SPECIFIC_TICKER)
            val result = api.getNutLinkSpecificTicker("ticker", QueryParameters())
            assertEquals(content, result)
        }

    @Test
    fun testGetNutLinkSpecificTickerReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                NutLinkApi.PATH_GET_NUTLINK_SPECIFIC_TICKER,
                HttpStatusCode.OK,
            ) { it.getNutLinkSpecificTicker("ticker", QueryParameters()) }
        }

    @Test
    fun testGetNutLinkSpecificTickerReturn400() =
        runTest {
            testApiWithBadRequest(
                NutLinkApi.PATH_GET_NUTLINK_SPECIFIC_TICKER,
                HttpStatusCode.BadRequest,
            ) { it.getNutLinkSpecificTicker("ticker", QueryParameters()) }
        }

    private fun createNutlinkApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = NutLinkApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":address", "address")
                .replace(":ticker", "ticker"),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (NutLinkApi) -> Unit,
    ) {
        val api =
            createNutlinkApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
