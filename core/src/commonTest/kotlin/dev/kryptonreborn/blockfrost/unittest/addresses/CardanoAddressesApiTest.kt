package dev.kryptonreborn.blockfrost.unittest.addresses

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi
import dev.kryptonreborn.blockfrost.addresses.model.AddressDetail
import dev.kryptonreborn.blockfrost.addresses.model.AddressTransaction
import dev.kryptonreborn.blockfrost.addresses.model.AddressUTXO
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BaseQueryParameters
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
    val baseQueryParameters = BaseQueryParameters()

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

    @Test
    fun testGetAddressDetailReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/address_detail.json"
            val expectedData =
                Ktor.json.decodeFromString<AddressDetail>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_ADDRESS_DETAIL,
                )
            val result = api.getAddressDetail(address)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetAddressDetailReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_DETAIL,
                HttpStatusCode.OK,
            ) { api ->
                api.getAddressDetail(address)
            }
        }

    @Test
    fun testGetAddressDetailReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_DETAIL,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getAddressDetail(address)
            }
        }

    @Test
    fun testGetAddressUtxosReturn200() =
        runTest {
            val resource = "src/commonTest/resources/api_address_utxos_200.json"
            val expectedData =
                Ktor.json.decodeFromString<List<AddressUTXO>>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_ADDRESS_UTXOS,
                )
            val result = api.getAddressUtxos(address, baseQueryParameters)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetAddressUtxosReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_UTXOS,
                HttpStatusCode.OK,
            ) { api ->
                api.getAddressUtxos(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressUtxosReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_UTXOS,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getAddressUtxos(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressUtxosAssetsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/api_address_utxos_200.json"
            val expectedData =
                Ktor.json.decodeFromString<List<AddressUTXO>>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_ADDRESS_UTXOS_ASSETS,
                )
            val result = api.getAddressUtxosAssets(address, baseQueryParameters)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetAddressUtxosAssetsReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_UTXOS_ASSETS,
                HttpStatusCode.OK,
            ) { api ->
                api.getAddressUtxosAssets(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressUtxosAssetsReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_UTXOS_ASSETS,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getAddressUtxosAssets(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressTransactionsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/api_address_transactions_200.json"
            val expectedData =
                Ktor.json.decodeFromString<List<AddressTransaction>>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_ADDRESS_TRANSACTIONS,
                )
            val result = api.getAddressTransactions(address, baseQueryParameters)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetAddressTransactionsReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_TRANSACTIONS,
                HttpStatusCode.OK,
            ) { api ->
                api.getAddressTransactions(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressTransactionsReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_TRANSACTIONS,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getAddressTransactions(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressTxsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/api_address_txs_200.json"
            val expectedData =
                Ktor.json.decodeFromString<List<String>>(Resource(resource).readText())
            val api =
                createAddressApi(
                    resource,
                    CardanoAddressApi.PATH_ADDRESS_TXS,
                )
            val result = api.getAddressTxs(address, baseQueryParameters)
            assertEquals(result, expectedData)
        }

    @Test
    fun testGetAddressTxsReturn200WithFailData() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_TXS,
                HttpStatusCode.OK,
            ) { api ->
                api.getAddressTxs(address, baseQueryParameters)
            }
        }

    @Test
    fun testGetAddressTxsReturnBadRequest() =
        runTest {
            testApiWithFailRequest(
                CardanoAddressApi.PATH_ADDRESS_TXS,
                HttpStatusCode.BadRequest,
            ) { api ->
                api.getAddressTxs(address, baseQueryParameters)
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
