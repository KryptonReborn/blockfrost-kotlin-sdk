package dev.kryptonreborn.blockfrost.unittest.transactions

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_SPECIFIC_TRANSACTION
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_DELEGATIONS
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_METADATA
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_METADATA_CBOR
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_MIRS
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_POOL_RETIRES
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_POOL_UPDATES
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_REDEEMERS
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_STAKES
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_UTXOS
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_GET_TRANSACTION_WITHDRAWS
import dev.kryptonreborn.blockfrost.transactions.CardanoTransactionsApi.Companion.PATH_SUBMIT_TRANSACTION
import dev.kryptonreborn.blockfrost.transactions.model.DelegationCertificate
import dev.kryptonreborn.blockfrost.transactions.model.SpecificTransaction
import dev.kryptonreborn.blockfrost.transactions.model.StakeAddressCertificate
import dev.kryptonreborn.blockfrost.transactions.model.StakePoolCertificate
import dev.kryptonreborn.blockfrost.transactions.model.StakePoolRetirementCertificate
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMIR
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadata
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadataCbor
import dev.kryptonreborn.blockfrost.transactions.model.TransactionRedeemer
import dev.kryptonreborn.blockfrost.transactions.model.TransactionUtxos
import dev.kryptonreborn.blockfrost.transactions.model.TransactionWithdrawal
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoTransactionApiTest {
    private val anyString = "anyString"

    @Test
    fun testGetSpecificTransactionReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_transaction.json"
            val expectedData = resource.resourceToExpectedData<SpecificTransaction>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_SPECIFIC_TRANSACTION,
                )
            val result = api.getSpecificTransaction(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetSpecificTransactionReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SPECIFIC_TRANSACTION,
                HttpStatusCode.OK,
            ) { it.getSpecificTransaction(anyString) }
        }

    @Test
    fun testGetSpecificTransactionReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SPECIFIC_TRANSACTION,
                HttpStatusCode.BadRequest,
            ) { it.getSpecificTransaction(anyString) }
        }

    @Test
    fun testGetTransactionUtxosReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/transaction_utxos.json"
            val expectedData = resource.resourceToExpectedData<TransactionUtxos>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_UTXOS,
                )
            val result = api.getTransactionUtxos(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionUtxosReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_UTXOS,
                HttpStatusCode.OK,
            ) { it.getTransactionUtxos(anyString) }
        }

    @Test
    fun testGetTransactionUtxosReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_UTXOS,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionUtxos(anyString) }
        }

    @Test
    fun testGetTransactionStakesReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_address_certificates.json"
            val expectedData = resource.resourceToExpectedData<List<StakeAddressCertificate>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_STAKES,
                )
            val result = api.getTransactionStakes(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionStakesReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_STAKES,
                HttpStatusCode.OK,
            ) { it.getTransactionStakes(anyString) }
        }

    @Test
    fun testGetTransactionStakesReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_STAKES,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionStakes(anyString) }
        }

    @Test
    fun testGetTransactionDelegationsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_delegation_certificates.json"
            val expectedData = resource.resourceToExpectedData<List<DelegationCertificate>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_DELEGATIONS,
                )
            val result = api.getTransactionDelegations(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionDelegationsReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_DELEGATIONS,
                HttpStatusCode.OK,
            ) { it.getTransactionDelegations(anyString) }
        }

    @Test
    fun testGetTransactionDelegationsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_DELEGATIONS,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionDelegations(anyString) }
        }

    @Test
    fun testGetTransactionWithdrawalsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_transaction_withdraw.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionWithdrawal>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_WITHDRAWS,
                )
            val result = api.getTransactionWithdrawals(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionWithdrawalsReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_WITHDRAWS,
                HttpStatusCode.OK,
            ) { it.getTransactionWithdrawals(anyString) }
        }

    @Test
    fun testGetTransactionWithdrawalsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_WITHDRAWS,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionWithdrawals(anyString) }
        }

    @Test
    fun testGetTransactionMirsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_transaction_mirs.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionMIR>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_MIRS,
                )
            val result = api.getTransactionMirs(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionMirsReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_MIRS,
                HttpStatusCode.OK,
            ) { it.getTransactionMirs(anyString) }
        }

    @Test
    fun testGetTransactionMirsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_MIRS,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionMirs(anyString) }
        }

    @Test
    fun testGetTransactionPoolUpdatesReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_certificate.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolCertificate>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_POOL_UPDATES,
                )
            val result = api.getTransactionPoolUpdates(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionPoolUpdatesReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_POOL_UPDATES,
                HttpStatusCode.OK,
            ) { it.getTransactionPoolUpdates(anyString) }
        }

    @Test
    fun testGetTransactionPoolUpdatesReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_POOL_UPDATES,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionPoolUpdates(anyString) }
        }

    @Test
    fun testGetTransactionPoolRetiresReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_stake_pool_retirement_certificate.json"
            val expectedData = resource.resourceToExpectedData<List<StakePoolRetirementCertificate>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_POOL_RETIRES,
                )
            val result = api.getTransactionPoolRetires(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionPoolRetiresReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_POOL_RETIRES,
                HttpStatusCode.OK,
            ) { it.getTransactionPoolRetires(anyString) }
        }

    @Test
    fun testGetTransactionPoolRetiresReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_POOL_RETIRES,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionPoolRetires(anyString) }
        }

    @Test
    fun testGetTransactionMetadataReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_transaction_metadata.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionMetadata>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_METADATA,
                )
            val result = api.getTransactionMetadata(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionMetadataReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_METADATA,
                HttpStatusCode.OK,
            ) { it.getTransactionMetadata(anyString) }
        }

    @Test
    fun testGetTransactionMetadataReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_METADATA,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionMetadata(anyString) }
        }

    @Test
    fun testGetTransactionMetadataCborReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_transaction_metadata_cbor.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionMetadataCbor>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_METADATA_CBOR,
                )
            val result = api.getTransactionMetadataCbor(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionMetadataCborReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_METADATA_CBOR,
                HttpStatusCode.OK,
            ) { it.getTransactionMetadataCbor(anyString) }
        }

    @Test
    fun testGetTransactionMetadataCborReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_METADATA_CBOR,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionMetadataCbor(anyString) }
        }

    @Test
    fun testGetTransactionRedeemersReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_transaction_redeemer.json"
            val expectedData = resource.resourceToExpectedData<List<TransactionRedeemer>>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_GET_TRANSACTION_REDEEMERS,
                )
            val result = api.getTransactionRedeemers(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetTransactionRedeemersReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_REDEEMERS,
                HttpStatusCode.OK,
            ) { it.getTransactionRedeemers(anyString) }
        }

    @Test
    fun testGetTransactionRedeemersReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_TRANSACTION_REDEEMERS,
                HttpStatusCode.BadRequest,
            ) { it.getTransactionRedeemers(anyString) }
        }

    @Test
    fun testSubmitTransaction() =
        runTest {
            val resource = "src/commonTest/resources/test_data.json"
            val expectedData = resource.resourceToExpectedData<JsonObject>()
            val api =
                createCardanoTransactionsApi(
                    resource,
                    PATH_SUBMIT_TRANSACTION,
                )
            val result = api.submitTransaction(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testSubmitTransactionReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_SUBMIT_TRANSACTION,
                HttpStatusCode.OK,
            ) { it.submitTransaction(anyString) }
        }

    @Test
    fun testSubmitTransactionReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_SUBMIT_TRANSACTION,
                HttpStatusCode.BadRequest,
            ) { it.submitTransaction(anyString) }
        }

    private fun createCardanoTransactionsApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoTransactionsApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":hash", anyString),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoTransactionsApi) -> Unit,
    ) {
        val api =
            createCardanoTransactionsApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
