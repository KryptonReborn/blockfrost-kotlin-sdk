package dev.kryptonreborn.blockfrost.ipfs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Pinned Object
 *
 * @property ipfsHash IPFS hash of the pinned object
 * @property state State of the pin action (queued, pinned, unpinned, failed, gc)
 */
@Serializable
data class PinnedObject(
    @SerialName("ipfs_hash") val ipfsHash: String,
    val state: ObjectState,
)
