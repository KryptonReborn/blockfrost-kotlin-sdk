package dev.kryptonreborn.blockfrost.metadata.model

import kotlinx.serialization.Serializable

/**
 * Transaction Metadata Label
 *
 * @property label Metadata label
 * @property cip10 CIP10 defined description
 * @property count The count of metadata entries with a specific label
 */
@Serializable
data class TransactionMetadataLabel(
    val label: String,
    val cip10: String?,
    val count: String,
)
