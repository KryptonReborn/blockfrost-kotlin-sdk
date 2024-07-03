package dev.kryptonreborn.blockfrost.unittest.scripts

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPTS
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_CBOR
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_DATUM
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_DATUM_CBOR
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_JSON
import dev.kryptonreborn.blockfrost.scripts.CardanoScriptsApi.Companion.PATH_GET_SCRIPT_REDEEMERS
import dev.kryptonreborn.blockfrost.scripts.model.DatumCborValue
import dev.kryptonreborn.blockfrost.scripts.model.DatumValue
import dev.kryptonreborn.blockfrost.scripts.model.Script
import dev.kryptonreborn.blockfrost.scripts.model.ScriptCbor
import dev.kryptonreborn.blockfrost.scripts.model.ScriptJson
import dev.kryptonreborn.blockfrost.scripts.model.ScriptRedeemer
import dev.kryptonreborn.blockfrost.scripts.model.SpecificScript
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CardanoScriptsApiTest {
    private val queryParameters = QueryParameters()
    private val anyString = "anyString"

    @Test
    fun testGetScriptsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_scripts.json"
            val expectedData = resource.resourceToExpectedData<List<Script>>()
            val api =
                createCardanoScriptsApi(
                    resource,
                    PATH_GET_SCRIPTS,
                )
            val result = api.getScripts(queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptsReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPTS,
                HttpStatusCode.OK,
            ) { it.getScripts(queryParameters) }
        }

    @Test
    fun testGetScriptsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPTS,
                HttpStatusCode.BadRequest,
            ) { it.getScripts(queryParameters) }
        }

    @Test
    fun testGetScriptReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/specific_script.json"
            val expectedData = resource.resourceToExpectedData<SpecificScript>()
            val api = createCardanoScriptsApi(resource, PATH_GET_SCRIPT)
            val result = api.getScript(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT,
                HttpStatusCode.OK,
            ) { it.getScript(anyString) }
        }

    @Test
    fun testGetScriptReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT,
                HttpStatusCode.BadRequest,
            ) { it.getScript(anyString) }
        }

    @Test
    fun testGetScriptJsonReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/script_json.json"
            val expectedData = resource.resourceToExpectedData<ScriptJson>()
            val api = createCardanoScriptsApi(resource, PATH_GET_SCRIPT_JSON)
            val result = api.getScriptJson(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptJsonReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_JSON,
                HttpStatusCode.OK,
            ) { it.getScriptJson(anyString) }
        }

    @Test
    fun testGetScriptJsonReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_JSON,
                HttpStatusCode.BadRequest,
            ) { it.getScriptJson(anyString) }
        }

    @Test
    fun testGetScriptCborReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/script_cbor.json"
            val expectedData = resource.resourceToExpectedData<ScriptCbor>()
            val api = createCardanoScriptsApi(resource, PATH_GET_SCRIPT_CBOR)
            val result = api.getScriptCbor(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptCborReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_CBOR,
                HttpStatusCode.OK,
            ) { it.getScriptCbor(anyString) }
        }

    @Test
    fun testGetScriptCborReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_CBOR,
                HttpStatusCode.BadRequest,
            ) { it.getScriptCbor(anyString) }
        }

    @Test
    fun testGetScriptDatumReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/datum_value.json"
            val expectedData = resource.resourceToExpectedData<DatumValue>()
            val api = createCardanoScriptsApi(resource, PATH_GET_SCRIPT_DATUM)
            val result = api.getScriptDatum(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptDatumReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_DATUM,
                HttpStatusCode.OK,
            ) { it.getScriptDatum(anyString) }
        }

    @Test
    fun testGetScriptDatumReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_DATUM,
                HttpStatusCode.BadRequest,
            ) { it.getScriptDatum(anyString) }
        }

    @Test
    fun testGetScriptDatumCborReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/datum_cbor_value.json"
            val expectedData = resource.resourceToExpectedData<DatumCborValue>()
            val api = createCardanoScriptsApi(resource, PATH_GET_SCRIPT_DATUM_CBOR)
            val result = api.getScriptDatumCbor(anyString)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptDatumCborReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_DATUM_CBOR,
                HttpStatusCode.OK,
            ) { it.getScriptDatumCbor(anyString) }
        }

    @Test
    fun testGetScriptDatumCborReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_DATUM_CBOR,
                HttpStatusCode.BadRequest,
            ) { it.getScriptDatumCbor(anyString) }
        }

    @Test
    fun testGetScriptRedeemersReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_script_redeemer.json"
            val expectedData = resource.resourceToExpectedData<List<ScriptRedeemer>>()
            val api = createCardanoScriptsApi(resource, PATH_GET_SCRIPT_REDEEMERS)
            val result = api.getScriptRedeemers(anyString, queryParameters)
            assertEquals(expectedData, result)
        }

    @Test
    fun testGetScriptRedeemersReturn200WithFailure() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_REDEEMERS,
                HttpStatusCode.OK,
            ) { it.getScriptRedeemers(anyString, queryParameters) }
        }

    @Test
    fun testGetScriptRedeemersReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_GET_SCRIPT_REDEEMERS,
                HttpStatusCode.BadRequest,
            ) { it.getScriptRedeemers(anyString, queryParameters) }
        }

    private fun createCardanoScriptsApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoScriptsApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":script_hash", anyString)
                .replace(":datum_hash", anyString),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoScriptsApi) -> Unit,
    ) {
        val api =
            createCardanoScriptsApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
