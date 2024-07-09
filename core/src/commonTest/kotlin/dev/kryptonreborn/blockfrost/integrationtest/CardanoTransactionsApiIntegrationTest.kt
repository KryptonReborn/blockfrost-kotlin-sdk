package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.transactions.model.DelegationCertificate
import dev.kryptonreborn.blockfrost.transactions.model.SpecificTransaction
import dev.kryptonreborn.blockfrost.transactions.model.StakeAddressCertificate
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMIR
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadata
import dev.kryptonreborn.blockfrost.transactions.model.TransactionMetadataCbor
import dev.kryptonreborn.blockfrost.transactions.model.TransactionRedeemer
import dev.kryptonreborn.blockfrost.transactions.model.TransactionUtxos
import dev.kryptonreborn.blockfrost.transactions.model.TransactionWithdrawal
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoTransactionsApiIntegrationTest : BaseIntegrationTest() {
    private val hash = "37746d2fa855de3095792d2e534deea9f1dbb43a113eec5d1dfad3963d8bb09d"

    @Test
    fun testGetSpecificTransaction() =
        runIntegrationTest {
            val result = blockfrostClient.getSpecificTransaction(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is SpecificTransaction)
        }

    @Test
    fun testGetTransactionUtxos() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionUtxos(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is TransactionUtxos)
        }

    @Test
    fun testGetTransactionStakes() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionStakes(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<StakeAddressCertificate>)
        }

    @Test
    fun testGetTransactionDelegations() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionDelegations(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<DelegationCertificate>)
        }

    @Test
    fun testGetTransactionWithdrawals() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionWithdrawals(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionWithdrawal>)
        }

    @Test
    fun testGetTransactionMirs() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionMirs(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionMIR>)
        }

    @Test
    fun testGetTransactionMetadata() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionMetadata(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionMetadata>)
        }

    @Test
    fun testGetTransactionMetadataCbor() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionMetadataCbor(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionMetadataCbor>)
        }

    @Test
    fun testGetTransactionRedeemers() =
        runIntegrationTest {
            val result = blockfrostClient.getTransactionRedeemers(hash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<TransactionRedeemer>)
        }
}
