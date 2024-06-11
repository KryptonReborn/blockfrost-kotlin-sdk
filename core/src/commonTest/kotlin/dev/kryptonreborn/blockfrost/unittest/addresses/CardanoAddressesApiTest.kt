package dev.kryptonreborn.blockfrost.unittest.addresses

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.addresses.CardanoAddressApi
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.ktor.Ktor
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

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
