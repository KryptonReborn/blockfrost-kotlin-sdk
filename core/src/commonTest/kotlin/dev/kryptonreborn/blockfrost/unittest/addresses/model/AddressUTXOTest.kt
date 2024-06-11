package dev.kryptonreborn.blockfrost.unittest.addresses.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.addresses.model.AddressUTXO
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AddressUTXOTest {
    @Test
    fun testDeserialize() {
        val json = Resource("src/commonTest/resources/model/address_utxo.json").readText()
        val content = Ktor.json.decodeFromString<AddressUTXO>(json)
        assertEquals(
            "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
            content.address,
        )
        assertEquals(1, content.amount.size)
        assertEquals("lovelace", content.amount[0].unit)
        assertEquals("42000000", content.amount[0].quantity)
        assertEquals(null, content.inlineDatum)
        assertEquals(0, content.outputIndex)
        assertEquals(
            null,
            content.referenceScriptHash,
        )
        assertEquals(
            "39a7a284c2a0948189dc45dec670211cd4d72f7b66c5726c08d9b3df11e44d58",
            content.txHash,
        )
    }
}
