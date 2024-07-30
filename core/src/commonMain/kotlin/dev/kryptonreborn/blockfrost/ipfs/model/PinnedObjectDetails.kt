package dev.kryptonreborn.blockfrost.ipfs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Pinned Object Details
 *
 * @property timeCreated Creation time of the IPFS object on our backends
 * @property timePinned Pin time of the IPFS object on our backends
 * @property ipfsHash IPFS hash of the pinned object
 * @property size Size of the object in Bytes
 * @property state State of the pinned object (queued, pinned, unpinned, failed, gc)
 */
@Serializable
data class PinnedObjectDetails(
    @SerialName("time_created") val timeCreated: Long,
    @SerialName("time_pinned") val timePinned: Long,
    @SerialName("ipfs_hash") val ipfsHash: String,
    val size: String,
    val state: ObjectState,
)
