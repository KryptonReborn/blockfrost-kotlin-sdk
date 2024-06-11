package dev.kryptonreborn.blockfrost.unittest.addresses

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.ktor.Ktor
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoAddressesApiTest {
    private val address = "address"

    @Test
    fun testGetSpecificAddressReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_address.json"
            val expectedData =
                Ktor.json.decodeFromString<SpecificAddress>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_SPECIFIC_ADDRESSES,
                )
            val result = api.getSpecificAddress(address)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetSpecificAddressReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_SPECIFIC_ADDRESSES,
                HttpStatusCode.OK,
            ) { api ->
                api.getSpecificAddress(address)
            }
        }

    @Test
    fun testGetSpecificAddressReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_SPECIFIC_ADDRESSES,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getSpecificAddress(address)
            }
        }

    @Test
    fun testGetSpecificAddressExtendedReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_address.json"
            val expectedData =
                Ktor.json.decodeFromString<SpecificAddress>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_SPECIFIC_ADDRESSES_EXTENDED,
                )
            val result = api.getSpecificAddressExtended(address)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetSpecificAddressExtendedReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_SPECIFIC_ADDRESSES_EXTENDED,
                HttpStatusCode.OK,
            ) { api ->
                api.getSpecificAddressExtended(address)
            }
        }

    @Test
    fun testGetSpecificAddressExtendedReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_SPECIFIC_ADDRESSES_EXTENDED,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getSpecificAddressExtended(address)
            }
        }

    private suspend fun testApiWithFailRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoAddressApi) -> Unit,
    ) {
        val accountsApi =
            createAddressApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(accountsApi) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }

    private fun createAddressApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoAddressApi(
        TestKtorClient.createMockHttpClient(
            path.replace(
                ":address",
                address,
            ),
            Resource(resource).readText(),
            status,
        ),
    )
}
