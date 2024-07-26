package dev.kryptonreborn.blockfrost.ipfs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Added IPFS Object
 *
 * @property name Name of the file
 * @property ipfsHash IPFS hash of the file
 * @property size IPFS node size in Bytes
 */
@Serializable
data class AddedIpfsObject(
    val name: String,
    @SerialName("ipfs_hash") val ipfsHash: String,
    val size: String,
)
