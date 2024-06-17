package dev.kryptonreborn.blockfrost.unittest.epochs

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_LATEST_EPOCH
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS
import dev.kryptonreborn.blockfrost.epochs.CardanoEpochsApi.Companion.PATH_SPECIFIC_EPOCH
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoEpochApiTest {
    private val anyString = "anyString"
    private val epoch = 1
    private val queryParameters = QueryParameters()

    @Test
    fun testGetLatestEpoch200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/model/epoch.json",
                    PATH_LATEST_EPOCH,
                )
            val content = api.getLatestEpoch()
            assertEquals(225, content.epoch)
            assertEquals(1603403091, content.startTime)
            assertEquals(1603835086, content.endTime)
            assertEquals(1603403092, content.firstBlockTime)
            assertEquals(1603835084, content.lastBlockTime)
            assertEquals(21298, content.blockCount)
            assertEquals(17856, content.txCount)
            assertEquals("7849943934049314", content.output)
            assertEquals("4203312194", content.fees)
            assertEquals("784953934049314", content.activeStake)
        }

    @Test
    fun testGetLatestEpochReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_LATEST_EPOCH,
                HttpStatusCode.OK,
            ) { it.getLatestEpoch() }
        }

    @Test
    fun testGetLatestEpochReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_LATEST_EPOCH,
                HttpStatusCode.BadRequest,
            ) { it.getLatestEpoch() }
        }

    @Test
    fun testGetLatestEpochProtocolParameters200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/model/protocol_parameters.json",
                    PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS,
                )
            val content = api.getLatestEpochProtocolParameters()
            assertEquals(225, content.epoch)
            assertEquals(44, content.minFeeA)
            assertEquals(155381, content.minFeeB)
            assertEquals(65536, content.maxBlockSize)
            assertEquals(16384, content.maxTxSize)
            assertEquals(1100, content.maxBlockHeaderSize)
            assertEquals("2000000", content.keyDeposit)
            assertEquals("500000000", content.poolDeposit)
            assertEquals(18, content.eMax)
            assertEquals(150, content.nOpt)
            assertEquals(0.3, content.a0)
            assertEquals(0.003, content.rho)
            assertEquals(0.2, content.tau)
            assertEquals(0.5, content.decentralisationParam)
            assertEquals(null, content.extraEntropy)
            assertEquals(2, content.protocolMajorVer)
            assertEquals(0, content.protocolMinorVer)
            assertEquals("1000000", content.minUtxo)
            assertEquals("340000000", content.minPoolCost)
            assertEquals(
                "1a3be38bcbb7911969283716ad7aa550250226b76a61fc51cc9a9a35d9276d81",
                content.nonce,
            )
            assertTrue(content.costModels is Map<String, JsonElement>)
            assertEquals(0.0577, content.priceMem)
            assertEquals(0.0000721, content.priceStep)
            assertEquals("10000000", content.maxTxExMem)
            assertEquals("10000000000", content.maxTxExSteps)
            assertEquals("50000000", content.maxBlockExMem)
            assertEquals("40000000000", content.maxBlockExSteps)
            assertEquals("5000", content.maxValSize)
            assertEquals(150, content.collateralPercent)
            assertEquals(3, content.maxCollateralInputs)
            assertEquals("34482", content.coinsPerUtxoSize)
        }

    @Test
    fun testGetLatestEpochProtocolParametersReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS,
                HttpStatusCode.OK,
            ) { it.getLatestEpochProtocolParameters() }
        }

    @Test
    fun testGetLatestEpochProtocolParametersReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS,
                HttpStatusCode.BadRequest,
            ) { it.getLatestEpochProtocolParameters() }
        }

    @Test
    fun testGetSpecificEpoch200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/model/epoch.json",
                    PATH_SPECIFIC_EPOCH,
                )
            val content = api.getSpecificEpoch(epoch)
            assertEquals(225, content.epoch)
            assertEquals(1603403091, content.startTime)
            assertEquals(1603835086, content.endTime)
            assertEquals(1603403092, content.firstBlockTime)
            assertEquals(1603835084, content.lastBlockTime)
            assertEquals(21298, content.blockCount)
            assertEquals(17856, content.txCount)
            assertEquals("7849943934049314", content.output)
            assertEquals("4203312194", content.fees)
            assertEquals("784953934049314", content.activeStake)
        }

    @Test
    fun testGetSpecificEpochReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_SPECIFIC_EPOCH,
                HttpStatusCode.OK,
            ) { it.getSpecificEpoch(epoch) }
        }

    @Test
    fun testGetSpecificEpochReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_SPECIFIC_EPOCH,
                HttpStatusCode.BadRequest,
            ) { it.getSpecificEpoch(epoch) }
        }

    @Test
    fun testGetListNextEpochs200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/list_epochs.json",
                    CardanoEpochsApi.PATH_LIST_NEXT_EPOCHS,
                )
            val content = api.getListNextEpochs(epoch, queryParameters)
            assertEquals(225, content[0].epoch)
            assertEquals(1603403091, content[0].startTime)
            assertEquals(1603835086, content[0].endTime)
            assertEquals(1603403092, content[0].firstBlockTime)
            assertEquals(1603835084, content[0].lastBlockTime)
            assertEquals(21298, content[0].blockCount)
            assertEquals(17856, content[0].txCount)
            assertEquals("7849943934049314", content[0].output)
            assertEquals("4203312194", content[0].fees)
            assertEquals("784953934049314", content[0].activeStake)
        }

    @Test
    fun testGetListNextEpochsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_LIST_NEXT_EPOCHS,
                HttpStatusCode.OK,
            ) { it.getListNextEpochs(epoch, queryParameters) }
        }

    @Test
    fun testGetListNextEpochsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_LIST_NEXT_EPOCHS,
                HttpStatusCode.BadRequest,
            ) { it.getListNextEpochs(epoch, queryParameters) }
        }

    @Test
    fun testGetListPreviousEpochs200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/list_epochs.json",
                    CardanoEpochsApi.PATH_LIST_PREVIOUS_EPOCHS,
                )
            val content = api.getListPreviousEpochs(epoch, queryParameters)
            assertEquals(225, content[0].epoch)
            assertEquals(1603403091, content[0].startTime)
            assertEquals(1603835086, content[0].endTime)
            assertEquals(1603403092, content[0].firstBlockTime)
            assertEquals(1603835084, content[0].lastBlockTime)
            assertEquals(21298, content[0].blockCount)
            assertEquals(17856, content[0].txCount)
            assertEquals("7849943934049314", content[0].output)
            assertEquals("4203312194", content[0].fees)
            assertEquals("784953934049314", content[0].activeStake)
        }

    @Test
    fun testGetListPreviousEpochsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_LIST_PREVIOUS_EPOCHS,
                HttpStatusCode.OK,
            ) { it.getListPreviousEpochs(epoch, queryParameters) }
        }

    @Test
    fun testGetListPreviousEpochsReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_LIST_PREVIOUS_EPOCHS,
                HttpStatusCode.BadRequest,
            ) { it.getListPreviousEpochs(epoch, queryParameters) }
        }

    @Test
    fun testGetStakeDistribution200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/list_stake_info.json",
                    CardanoEpochsApi.PATH_STAKE_DISTRIBUTION,
                )
            val content = api.getStakeDistribution(epoch, queryParameters)
            assertEquals(1, content.size)
            assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content[0].poolId)
            assertEquals("4440295078", content[0].amount)
            assertEquals(
                "stake1u9l5q5jwgelgagzyt6nuaasefgmn8pd25c8e9qpeprq0tdcp0e3uk",
                content[0].stakeAddress,
            )
        }

    @Test
    fun testGetStakeDistributionReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_STAKE_DISTRIBUTION,
                HttpStatusCode.OK,
            ) { it.getStakeDistribution(epoch, queryParameters) }
        }

    @Test
    fun testGetStakeDistributionReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_STAKE_DISTRIBUTION,
                HttpStatusCode.BadRequest,
            ) { it.getStakeDistribution(epoch, queryParameters) }
        }

    @Test
    fun testGetStakeDistributionPool200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/list_stake_info.json",
                    CardanoEpochsApi.PATH_STAKE_DISTRIBUTION_POOL,
                )
            val content =
                api.getStakeDistributionPool(
                    epoch,
                    anyString,
                    queryParameters,
                )
            assertEquals(1, content.size)
            assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content[0].poolId)
            assertEquals("4440295078", content[0].amount)
            assertEquals(
                "stake1u9l5q5jwgelgagzyt6nuaasefgmn8pd25c8e9qpeprq0tdcp0e3uk",
                content[0].stakeAddress,
            )
        }

    @Test
    fun testGetStakeDistributionPoolReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_STAKE_DISTRIBUTION_POOL,
                HttpStatusCode.OK,
            ) {
                it.getStakeDistributionPool(
                    epoch,
                    anyString,
                    queryParameters,
                )
            }
        }

    @Test
    fun testGetStakeDistributionPoolReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_STAKE_DISTRIBUTION_POOL,
                HttpStatusCode.BadRequest,
            ) {
                it.getStakeDistributionPool(
                    epoch,
                    anyString,
                    queryParameters,
                )
            }
        }

    @Test
    fun testGetBlockDistribution200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/list_string.json",
                    CardanoEpochsApi.PATH_BLOCK_DISTRIBUTION,
                )
            val content = api.getBlockDistribution(epoch, queryParameters)
            assertEquals(3, content.size)
        }

    @Test
    fun testGetBlockDistributionReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_BLOCK_DISTRIBUTION,
                HttpStatusCode.OK,
            ) { it.getBlockDistribution(epoch, queryParameters) }
        }

    @Test
    fun testGetBlockDistributionReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_BLOCK_DISTRIBUTION,
                HttpStatusCode.BadRequest,
            ) { it.getBlockDistribution(epoch, queryParameters) }
        }

    @Test
    fun testGetBlockDistributionPool200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/list_string.json",
                    CardanoEpochsApi.PATH_BLOCK_DISTRIBUTION_POOL,
                )
            val content =
                api.getBlockDistributionPool(
                    epoch,
                    anyString,
                    queryParameters,
                )
            assertEquals(3, content.size)
        }

    @Test
    fun testGetBlockDistributionPoolReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_BLOCK_DISTRIBUTION_POOL,
                HttpStatusCode.OK,
            ) {
                it.getBlockDistributionPool(
                    epoch,
                    anyString,
                    queryParameters,
                )
            }
        }

    @Test
    fun testGetBlockDistributionPoolReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_BLOCK_DISTRIBUTION_POOL,
                HttpStatusCode.BadRequest,
            ) {
                it.getBlockDistributionPool(
                    epoch,
                    anyString,
                    queryParameters,
                )
            }
        }

    @Test
    fun testGetProtocolParameters200() =
        runTest {
            val api =
                createEpochApi(
                    "src/commonTest/resources/model/protocol_parameters.json",
                    CardanoEpochsApi.PATH_PROTOCOL_PARAMETERS,
                )
            val content = api.getProtocolParameters(epoch)
            assertEquals(225, content.epoch)
            assertEquals(44, content.minFeeA)
            assertEquals(155381, content.minFeeB)
            assertEquals(65536, content.maxBlockSize)
            assertEquals(16384, content.maxTxSize)
            assertEquals(1100, content.maxBlockHeaderSize)
            assertEquals("2000000", content.keyDeposit)
            assertEquals("500000000", content.poolDeposit)
            assertEquals(18, content.eMax)
            assertEquals(150, content.nOpt)
            assertEquals(0.3, content.a0)
            assertEquals(0.003, content.rho)
            assertEquals(0.2, content.tau)
            assertEquals(0.5, content.decentralisationParam)
            assertEquals(null, content.extraEntropy)
            assertEquals(2, content.protocolMajorVer)
            assertEquals(0, content.protocolMinorVer)
            assertEquals("1000000", content.minUtxo)
            assertEquals("340000000", content.minPoolCost)
            assertEquals(
                "1a3be38bcbb7911969283716ad7aa550250226b76a61fc51cc9a9a35d9276d81",
                content.nonce,
            )
            assertTrue(content.costModels is Map<String, JsonElement>)
            assertEquals(0.0577, content.priceMem)
            assertEquals(0.0000721, content.priceStep)
            assertEquals("10000000", content.maxTxExMem)
            assertEquals("10000000000", content.maxTxExSteps)
            assertEquals("50000000", content.maxBlockExMem)
            assertEquals("40000000000", content.maxBlockExSteps)
            assertEquals("5000", content.maxValSize)
            assertEquals(150, content.collateralPercent)
            assertEquals(3, content.maxCollateralInputs)
            assertEquals("34482", content.coinsPerUtxoSize)
        }

    @Test
    fun testGetProtocolParametersReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_PROTOCOL_PARAMETERS,
                HttpStatusCode.OK,
            ) { it.getProtocolParameters(epoch) }
        }

    @Test
    fun testGetProtocolParametersReturn400() =
        runTest {
            testApiWithBadRequest(
                CardanoEpochsApi.PATH_PROTOCOL_PARAMETERS,
                HttpStatusCode.BadRequest,
            ) { it.getProtocolParameters(epoch) }
        }

    private fun createEpochApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoEpochsApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":number", epoch.toString())
                .replace(":pool_id", anyString),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoEpochsApi) -> Unit,
    ) {
        val api =
            createEpochApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
