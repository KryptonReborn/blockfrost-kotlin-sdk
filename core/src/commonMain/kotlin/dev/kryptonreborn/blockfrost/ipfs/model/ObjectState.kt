package dev.kryptonreborn.blockfrost.ipfs.model

import kotlinx.serialization.SerialName

enum class ObjectState {
    @SerialName("queued")
    Queued,

    @SerialName("pinned")
    Pinned,

    @SerialName("unpinned")
    Unpinned,

    @SerialName("failed")
    Failed,

    @SerialName("gc")
    Gc,
}
