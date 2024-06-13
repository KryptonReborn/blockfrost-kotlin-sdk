package dev.kryptonreborn.blockfrost.unittest.addresses.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.addresses.model.AddressTransaction
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AddressTransactionTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/address_transaction.json").readText()
        val addressTransaction = Ktor.json.decodeFromString<AddressTransaction>(json)
        assertEquals(4547L, addressTransaction.blockHeight)
        assertEquals(1635505987L, addressTransaction.blockTime)
        assertEquals(
            "52e748c4dec58b687b90b0b40d383b9fe1f24c1a833b7395cdf07dd67859f46f",
            addressTransaction.txHash,
        )
        assertEquals(9L, addressTransaction.txIndex)
    }
}
