package dev.kryptonreborn.blockfrost.unittest.ipfs

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_ADD
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_GATEWAY
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_ADD
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_DETAILS
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_LIST
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_REMOVE
import dev.kryptonreborn.blockfrost.ipfs.model.AddedIpfsObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObjectDetails
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class IPFSApiTest {

    private val anyString = "anyString"

    @Test
    fun testAddFileToIPFSReturn200() = runTest {
        val resource = "src/commonTest/resources/model/added_ipfs_object.json"
        val content = resource.resourceToExpectedData<AddedIpfsObject>()
        val api = createIPFSApi(resource, PATH_IPFS_ADD)
        val result = api.addFileToIPFS(ByteArray(0), "filename")
        assertEquals(content, result)
    }

    @Test
    fun testAddFileToIPFSReturn200WithFailData() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_ADD,
            HttpStatusCode.OK,
        ) { it.addFileToIPFS(ByteArray(0), "filename") }
    }

    @Test
    fun testAddFileToIPFSReturn400() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_ADD,
            HttpStatusCode.BadRequest,
        ) { it.addFileToIPFS(ByteArray(0), "filename") }
    }

    @Test
    fun testRelayToIPFSGatewayReturn200() = runTest {
        val resource = "src/commonTest/resources/file/test.txt"
        val content = Resource(resource).readBytes()
        val api = createIPFSApi(resource, PATH_IPFS_GATEWAY)
        val result = api.relayToIPFSGateway(anyString)
        assertEquals(content.size, result.size)
    }

    @Test
    fun testRelayToIPFSGatewayReturn200WithFailData() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_GATEWAY,
            HttpStatusCode.OK,
        ) { it.relayToIPFSGateway(anyString) }
    }

    @Test
    fun testRelayToIPFSGatewayReturn400() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_GATEWAY,
            HttpStatusCode.BadRequest,
        ) { it.relayToIPFSGateway(anyString) }
    }

    @Test
    fun testPinIPFSObjectReturn200() = runTest {
        val resource = "src/commonTest/resources/model/pinned_object.json"
        val content = resource.resourceToExpectedData<PinnedObject>()
        val api = createIPFSApi(resource, PATH_IPFS_PIN_ADD)
        val result = api.pinIPFSObject(anyString)
        assertEquals(content, result)
    }

    @Test
    fun testPinIPFSObjectReturn200WithFailData() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_ADD,
            HttpStatusCode.OK,
        ) { it.pinIPFSObject(anyString) }
    }

    @Test
    fun testPinIPFSObjectReturn400() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_ADD,
            HttpStatusCode.BadRequest,
        ) { it.pinIPFSObject(anyString) }
    }

    @Test
    fun testUnpinIPFSObjectReturn200() = runTest {
        val resource = "src/commonTest/resources/model/pinned_object.json"
        val content = resource.resourceToExpectedData<PinnedObject>()
        val api = createIPFSApi(resource, PATH_IPFS_PIN_REMOVE)
        val result = api.removePinnedObject(anyString)
        assertEquals(content, result)
    }

    @Test
    fun testUnpinIPFSObjectReturn200WithFailData() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_REMOVE,
            HttpStatusCode.OK,
        ) { it.removePinnedObject(anyString) }
    }

    @Test
    fun testUnpinIPFSObjectReturn400() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_REMOVE,
            HttpStatusCode.BadRequest,
        ) { it.removePinnedObject(anyString) }
    }

    @Test
    fun testGetPinnedObjectDetailsReturn200() = runTest {
        val resource = "src/commonTest/resources/model/pinned_object_details.json"
        val content =
            resource.resourceToExpectedData<PinnedObjectDetails>()
        val api = createIPFSApi(resource, PATH_IPFS_PIN_DETAILS)
        val result = api.getPinnedObjectDetails(anyString)
        assertEquals(content, result)
    }

    @Test
    fun testGetPinnedObjectDetailsReturn200WithFailData() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_DETAILS,
            HttpStatusCode.OK,
        ) { it.getPinnedObjectDetails(anyString) }
    }

    @Test
    fun testGetPinnedObjectDetailsReturn400() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_DETAILS,
            HttpStatusCode.BadRequest,
        ) { it.getPinnedObjectDetails(anyString) }
    }

    @Test
    fun testGetListPinnedObjectsReturn200() = runTest {
        val resource = "src/commonTest/resources/list_pinned_object_details.json"
        val content = resource.resourceToExpectedData<List<PinnedObjectDetails>>()
        val api = createIPFSApi(resource, PATH_IPFS_PIN_LIST)
        val result = api.getListPinnedObjects(QueryParameters())
        assertEquals(content, result)
    }

    @Test
    fun testGetListPinnedObjectsReturn200WithFailData() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_LIST,
            HttpStatusCode.OK,
        ) { it.getListPinnedObjects(QueryParameters()) }
    }

    @Test
    fun testGetListPinnedObjectsReturn400() = runTest {
        testApiWithBadRequest(
            PATH_IPFS_PIN_LIST,
            HttpStatusCode.BadRequest,
        ) { it.getListPinnedObjects(QueryParameters()) }
    }

    private fun createIPFSApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = IPFSApi(
        TestKtorClient.createMockHttpClient(
            path.replace(":IPFS_path", anyString),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (IPFSApi) -> Unit,
    ) {
        val api =
            createIPFSApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(api) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}