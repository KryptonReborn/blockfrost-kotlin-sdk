@file:UseSerializers(BigIntegerSerializer::class)

package dev.kryptonreborn.blockfrost.metadata.model

import com.ionspin.kotlin.bignum.integer.BigInteger
import dev.kryptonreborn.blockfrost.base.BigIntegerSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

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
    val count: BigInteger,
)
