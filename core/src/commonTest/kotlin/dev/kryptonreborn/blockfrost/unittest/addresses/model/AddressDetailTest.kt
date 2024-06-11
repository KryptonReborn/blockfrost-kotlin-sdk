package dev.kryptonreborn.blockfrost.unittest.addresses.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.addresses.model.AddressDetail
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class AddressDetailTest {
    @Test
    fun testDeserialize() {
        val json = Resource("src/commonTest/resources/model/address_detail.json").readText()
        val content = Ktor.json.decodeFromString<AddressDetail>(json)
        assertEquals(
            "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
            content.address,
        )
        assertEquals(2, content.receivedSum.size)
        assertEquals("lovelace", content.receivedSum[0].unit)
        assertEquals("42000000", content.receivedSum[0].quantity)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            content.receivedSum[1].unit,
        )
        assertEquals("12", content.receivedSum[1].quantity)
        assertEquals(2, content.sentSum.size)
        assertEquals("lovelace", content.sentSum[0].unit)
        assertEquals("42000000", content.sentSum[0].quantity)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            content.sentSum[1].unit,
        )
        assertEquals("12", content.sentSum[1].quantity)
        assertEquals(12, content.txCount)
    }
}
