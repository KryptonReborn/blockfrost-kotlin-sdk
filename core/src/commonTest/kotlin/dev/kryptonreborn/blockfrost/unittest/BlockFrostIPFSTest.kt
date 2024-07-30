package dev.kryptonreborn.blockfrost.unittest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.BlockFrostIPFS
import dev.kryptonreborn.blockfrost.TestKtorClient.createMockHttpClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_ADD
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_GATEWAY
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_ADD
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_DETAILS
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_LIST
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi.Companion.PATH_IPFS_PIN_REMOVE
import dev.kryptonreborn.blockfrost.ipfs.model.AddedIpfsObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObjectDetails
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BlockFrostIPFSTest {
    private val anyString = "anyString"

    @Test
    fun testAddFileToIPFS() =
        runTest {
            val resource = "src/commonTest/resources/model/added_ipfs_object.json"
            val content = resource.resourceToExpectedData<AddedIpfsObject>()
            val httpClient =
                createMockHttpClient(
                    PATH_IPFS_ADD.replace(":IPFS_path", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostIPFS = BlockFrostIPFS(httpClient)
            val result = blockFrostIPFS.addFileToIPFS(ByteArray(0), "filename")
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testAddFileToIPFSFail() =
        runTest {
            testApiFail(
                PATH_IPFS_ADD.replace(":IPFS_path", anyString),
            ) { it.addFileToIPFS(ByteArray(0), "filename") }
        }

    @Test
    fun testRelayToIPFSGatewayReturn200() =
        runTest {
            val resource = "src/commonTest/resources/file/test.txt"
            val content = Resource(resource).readBytes()
            val httpClient =
                createMockHttpClient(
                    PATH_IPFS_GATEWAY.replace(":IPFS_path", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostIPFS = BlockFrostIPFS(httpClient)
            val result = blockFrostIPFS.relayToIPFSGateway(anyString)
            assertEquals(content.size, result.getOrNull()?.size)
        }

    @Test
    fun testRelayToIPFSGatewayFail() =
        runTest {
            testApiFail(
                PATH_IPFS_GATEWAY.replace(":IPFS_path", anyString),
            ) { it.relayToIPFSGateway(anyString) }
        }

    @Test
    fun testPinIPFSObjectReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/pinned_object.json"
            val content = resource.resourceToExpectedData<PinnedObject>()
            val httpClient =
                createMockHttpClient(
                    PATH_IPFS_PIN_ADD.replace(":IPFS_path", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostIPFS = BlockFrostIPFS(httpClient)
            val result = blockFrostIPFS.pinIPFSObject(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testPinIPFSObjectFail() =
        runTest {
            testApiFail(
                PATH_IPFS_PIN_ADD.replace(":IPFS_path", anyString),
            ) { it.pinIPFSObject(anyString) }
        }

    @Test
    fun testUnpinIPFSObjectReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/pinned_object.json"
            val content = resource.resourceToExpectedData<PinnedObject>()
            val httpClient =
                createMockHttpClient(
                    PATH_IPFS_PIN_REMOVE.replace(":IPFS_path", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostIPFS = BlockFrostIPFS(httpClient)
            val result = blockFrostIPFS.removePinnedObject(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testUnpinIPFSObjectFail() =
        runTest {
            testApiFail(
                PATH_IPFS_PIN_REMOVE.replace(":IPFS_path", anyString),
            ) { it.removePinnedObject(anyString) }
        }

    @Test
    fun testGetPinnedObjectDetailsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/model/pinned_object_details.json"
            val content =
                resource.resourceToExpectedData<PinnedObjectDetails>()
            val httpClient =
                createMockHttpClient(
                    PATH_IPFS_PIN_DETAILS.replace(":IPFS_path", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostIPFS = BlockFrostIPFS(httpClient)
            val result = blockFrostIPFS.getPinnedObjectDetails(anyString)
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetPinnedObjectDetailsFail() =
        runTest {
            testApiFail(
                PATH_IPFS_PIN_DETAILS.replace(":IPFS_path", anyString),
            ) { it.getPinnedObjectDetails(anyString) }
        }

    @Test
    fun testGetListPinnedObjectsReturn200() =
        runTest {
            val resource = "src/commonTest/resources/list_pinned_object_details.json"
            val content = resource.resourceToExpectedData<List<PinnedObjectDetails>>()
            val httpClient =
                createMockHttpClient(
                    PATH_IPFS_PIN_LIST.replace(":IPFS_path", anyString),
                    Resource(resource).readText(),
                )
            val blockFrostIPFS = BlockFrostIPFS(httpClient)

            val result = blockFrostIPFS.getListPinnedObjects(QueryParameters())
            assertEquals(content, result.getOrNull())
        }

    @Test
    fun testGetListPinnedObjectsFail() =
        runTest {
            testApiFail(
                PATH_IPFS_PIN_LIST.replace(":IPFS_path", anyString),
            ) { it.getListPinnedObjects(QueryParameters()) }
        }

    private fun testApiFail(
        path: String,
        block: suspend (BlockFrostIPFS) -> Result<*>,
    ) = runTest {
        val httpClient =
            createMockHttpClient(
                path,
                Resource("src/commonTest/resources/test_data_errors_response.json").readText(),
            )
        val blockFrostIPFS = BlockFrostIPFS(httpClient)
        val result = block(blockFrostIPFS)
        assertEquals(null, result.getOrNull())
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is BlockfrostException)
    }
}
