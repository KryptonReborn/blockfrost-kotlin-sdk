package dev.kryptonreborn.blockfrost

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ipfs.IPFSApi
import dev.kryptonreborn.blockfrost.ktor.Ktor
import io.ktor.client.HttpClient

/**
 * The `BlockFrostIPFS` class provides functionality for interacting with the Blockfrost IPFS API.
 *
 * @property httpClient The HTTP client used to make requests to the Blockfrost API.
 * @property ipfsApi The IPFS API.
 * @constructor Creates a `BlockFrostIPFS` instance.
 * @param blockfrostConfig The Blockfrost configuration.
 */
class BlockFrostIPFS {
    private val httpClient: HttpClient
    private val ipfsApi: IPFSApi

    constructor(blockfrostConfig: BlockfrostConfig) : this(Ktor.httpClient(blockfrostConfig))

    internal constructor(httpClient: HttpClient) {
        this.httpClient = httpClient
        this.ipfsApi = IPFSApi(httpClient)
    }

    /**
     * You need to /ipfs/pin/add an object to avoid it being garbage collected. This usage is being counted in your user account quota.
     *
     * @param bytes The file bytes to be added to IPFS.
     * @param fileName The name of the file.
     * @return The IPFS path of the added file.
     */
    suspend fun addFileToIPFS(
        bytes: ByteArray,
        fileName: String,
    ) = handleApiResult { ipfsApi.addFileToIPFS(bytes, fileName) }

    /**
     * Retrieve an object from the IPFS gateway (useful if you do not want to rely on a public gateway, such as ipfs.blockfrost.dev).
     *
     * @param IPFSPath The IPFS path of the object.
     * @return The object bytes.
     */
    suspend fun relayToIPFSGateway(IPFSPath: String) = handleApiResult { ipfsApi.relayToIPFSGateway(IPFSPath) }

    /**
     * Pinning is necessary to avoid regular garbage collection (deletion) of IPFS objects. Non-pinned objects are regularly being removed without prior notice. Pinned objects are counted in your user storage quota.
     *
     *  @param IPFSPath The IPFS path of the object.
     *  @return The pinned object details.
     */
    suspend fun pinIPFSObject(IPFSPath: String) = handleApiResult { ipfsApi.pinIPFSObject(IPFSPath) }

    /**
     * List objects pinned to local storage
     *
     * @param queryParameters The query parameters for the request.
     * @return The list of pinned objects.
     */
    suspend fun getListPinnedObjects(queryParameters: QueryParameters = QueryParameters()) =
        handleApiResult { ipfsApi.getListPinnedObjects(queryParameters) }

    /**
     * Get information about locally pinned IPFS object
     *
     * @param IPFSPath The IPFS path of the object.
     * @return The pinned object details.
     */
    suspend fun getPinnedObjectDetails(IPFSPath: String) = handleApiResult { ipfsApi.getPinnedObjectDetails(IPFSPath) }

    /**
     * Remove pinned objects from local storage
     *
     * @param IPFSPath The IPFS path of the object.
     * @return The unpinned object details.
     */
    suspend fun removePinnedObject(IPFSPath: String) = handleApiResult { ipfsApi.removePinnedObject(IPFSPath) }

    private inline fun <T> handleApiResult(block: () -> T): Result<T> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
