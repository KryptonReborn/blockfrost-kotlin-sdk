package dev.kryptonreborn.blockfrost.unittest.ledger

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient.createMockHttpClient
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.ledger.CardanoLedgerApi
import dev.kryptonreborn.blockfrost.ledger.CardanoLedgerApi.Companion.PATH_BLOCKCHAIN_GENESIS
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoLedgerApiTest {
    @Test
    fun testGetBlockchainGenesis200() =
        runTest {
            val resource = "src/commonTest/resources/model/blockchain_genesis.json"
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCKCHAIN_GENESIS,
                    Resource(resource).readText(),
                )
            val api = CardanoLedgerApi(httpClient)
            val content = api.getBlockchainGenesis()
            assertEquals(0.05, content.activeSlotsCoefficient)
            assertEquals(5, content.updateQuorum)
            assertEquals("45000000000000000", content.maxLovelaceSupply)
            assertEquals(764824073, content.networkMagic)
            assertEquals(432000, content.epochLength)
            assertEquals(1506203091, content.systemStart)
            assertEquals(129600, content.slotsPerKesPeriod)
            assertEquals(1, content.slotLength)
            assertEquals(62, content.maxKesEvolutions)
            assertEquals(2160, content.securityParam)
        }

    @Test
    fun testGetBlockchainGenesisReturn200WithFailData() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCKCHAIN_GENESIS,
                    Resource(resource).readText(),
                    HttpStatusCode.OK,
                )
            val api = CardanoLedgerApi(httpClient)
            val exception =
                assertFailsWith<BlockfrostException> {
                    api.getBlockchainGenesis()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testGetBlockchainGenesisReturn400() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val httpClient =
                createMockHttpClient(
                    PATH_BLOCKCHAIN_GENESIS,
                    Resource(resource).readText(),
                    HttpStatusCode.BadRequest,
                )
            val api = CardanoLedgerApi(httpClient)
            val exception =
                assertFailsWith<BlockfrostException> {
                    api.getBlockchainGenesis()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }
}
