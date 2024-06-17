package dev.kryptonreborn.blockfrost.blocks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @property time Block creation time in UNIX time
 * @property height Block number
 * @property hash Hash of the block
 * @property slot Slot number
 * @property epoch Epoch number
 * @property epochSlot Slot within the epoch
 * @property slotLeader Bech32 ID of the slot leader or specific block description in case there is no slot leader
 * @property txCount Number of transactions in the block
 * @property output Total output within the block in Lovelaces
 * @property fees Total fees within the block in Lovelaces
 * @property blockVrf VRF key of the block
 * @property previousBlock Hash of the previous block
 * @property nextBlock Hash of the next block
 * @property confirmations Number of block confirmations
 */

@Serializable
data class BlockContent(
    val time: Int,
    val height: Int?,
    val hash: String,
    val slot: Int?,
    val epoch: Int?,
    @SerialName("epoch_slot") val epochSlot: Int?,
    @SerialName("slot_leader") val slotLeader: String,
    val size: Int,
    @SerialName("tx_count") val txCount: Int,
    val output: String?,
    val fees: String?,
    @SerialName("block_vrf") val blockVrf: String?,
    @SerialName("op_cert") val opCert: String?,
    @SerialName("op_cert_counter") val opCertCounter: String?,
    @SerialName("previous_block") val previousBlock: String?,
    @SerialName("next_block") val nextBlock: String?,
    val confirmations: Int,
)
