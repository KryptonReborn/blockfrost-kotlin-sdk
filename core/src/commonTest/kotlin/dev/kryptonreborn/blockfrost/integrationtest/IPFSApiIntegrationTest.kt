package dev.kryptonreborn.blockfrost.integrationtest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.BlockFrostIPFS
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.NetworkType
import dev.kryptonreborn.blockfrost.ipfs.model.AddedIpfsObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObjectDetails
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class IPFSApiIntegrationTest : BaseIntegrationTest() {
    private lateinit var blockFrostIPFS: BlockFrostIPFS
    private val iPFSPath = "QmUCXMTcvuJpwHF3gABRr69ceQR2uEG2Fsik9CyWh8MUoQ"

    @BeforeTest
    fun setupIPFS() {
        blockFrostIPFS =
            BlockFrostIPFS(
                BlockfrostConfig(
                    projectId = "ipfsekXGfZImDIAFiT24O3Z6EqfdPEGrHcaM",
                    networkType = NetworkType.IPFS,
                ),
            )
    }

    @Test
    fun testAddFileToIPFS() =
        runIntegrationTest {
            val bytes = Resource("src/commonTest/resources/file/test.txt").readBytes()
            println(bytes)
            val result =
                blockFrostIPFS.addFileToIPFS(
                    bytes = Resource("src/commonTest/resources/file/test.txt").readBytes(),
                    fileName = "test.txt",
                )
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is AddedIpfsObject)
        }

    @Test
    fun testRelayToIPFSGateway() =
        runIntegrationTest {
            val result = blockFrostIPFS.relayToIPFSGateway(iPFSPath)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is ByteArray)
        }

    @Test
    fun testPinIPFSObject() =
        runIntegrationTest {
            val result = blockFrostIPFS.pinIPFSObject(iPFSPath)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is PinnedObject)
            val pinnedResult = blockFrostIPFS.getPinnedObjectDetails(iPFSPath)
            assertNotNull(pinnedResult.getOrNull())
            assertTrue(pinnedResult.getOrNull() is PinnedObjectDetails)
        }

    @Test
    fun testGetListPinnedObjects() =
        runIntegrationTest {
            val result = blockFrostIPFS.getListPinnedObjects()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<PinnedObjectDetails>)
        }

    @Test
    fun testRemovePinnedObject() =
        runIntegrationTest {
            val result = blockFrostIPFS.removePinnedObject(iPFSPath)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is PinnedObject)
        }
}
