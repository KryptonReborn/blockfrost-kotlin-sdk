package dev.kryptonreborn.blockfrost.blocks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Block(
    @SerialName("time")
    val time: Int,
    @SerialName("height")
    val height: Int?,
    @SerialName("hash")
    val hash: String,
    @SerialName("slot")
    val slot: Int?,
    @SerialName("epoch")
    val epoch: Int?,
    @SerialName("epoch_slot")
    val epochSlot: Int?,
    @SerialName("slot_leader")
    val slotLeader: String,
    @SerialName("size")
    val size: Int,
    @SerialName("tx_count")
    val txCount: Int,
    @SerialName("output")
    val output: String?,
    @SerialName("fees")
    val fees: String?,
    @SerialName("block_vrf")
    val blockVrf: String?,
    @SerialName("op_cert")
    val opCert: String?,
    @SerialName("op_cert_counter")
    val opCertCounter: String?,
    @SerialName("previous_block")
    val previousBlock: String?,
    @SerialName("next_block")
    val nextBlock: String?,
    @SerialName("confirmations")
    val confirmations: Int,
)
