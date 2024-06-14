package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.addresses.model.AddressDetail
import dev.kryptonreborn.blockfrost.addresses.model.AddressTransaction
import dev.kryptonreborn.blockfrost.addresses.model.AddressUTXO
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CardanoAddressApiIntegrationTest : BaseIntegrationTest() {
    private val address =
        "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz"
    private val asset = "00000002df633853f6a47465c9496721d2d5b1291b8398016c0e87ae6e7574636f696e"

    @Test
    fun testGetAddress() = runIntegrationTest {
        val result = blockfrostClient.getSpecificAddress(address)
        println(result.getOrNull())
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is SpecificAddress)
    }

    @Test
    fun testGetAddressExtended() = runIntegrationTest {
        val result = blockfrostClient.getSpecificAddressExtended(address)
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is SpecificAddress)
    }

    @Test
    fun testGetAddressDetail() = runIntegrationTest {
        val result = blockfrostClient.getAddressDetail(address)
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is AddressDetail)
    }

    @Test
    fun testGetAddressUtxos() = runIntegrationTest {
        val result = blockfrostClient.getAddressUtxos(address)
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is List<AddressUTXO>)
    }

    @Test
    fun testGetAddressUtxosAssets() = runIntegrationTest {
        val result = blockfrostClient.getAddressUtxosAssets(address, asset)
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is List<AddressUTXO>)
    }

    @Test
    fun testGetAddressTransactions() = runIntegrationTest {
        val result = blockfrostClient.getAddressTransactions(address)
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is List<AddressTransaction>)
    }

    @Test
    fun testGetAddressTxs() = runIntegrationTest {
        val result = blockfrostClient.getAddressTxs(address)
        assertNotNull(result.getOrNull())
        assertTrue(result.getOrNull() is List<String>)
    }

}