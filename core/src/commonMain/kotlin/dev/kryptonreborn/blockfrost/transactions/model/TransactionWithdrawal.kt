package dev.kryptonreborn.blockfrost.transactions.model

import kotlinx.serialization.Serializable

/**
 * Transaction Withdrawal
 *
 * @property address Bech32 withdrawal address
 * @property amount Withdrawal amount in Lovelaces
 */
@Serializable
data class TransactionWithdrawal(
    val address: String,
    val amount: String,
)
