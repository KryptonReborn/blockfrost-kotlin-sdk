package dev.kryptonreborn.blockfrost.unittest.addresses.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.addresses.model.SpecificAddress
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlin.test.Test
import kotlin.test.assertEquals

class SpecificAddressTest {
    @Test
    fun testDeserialize() {
        val json = Resource("src/commonTest/resources/model/specific_address.json").readText()
        val content = Ktor.json.decodeFromString<SpecificAddress>(json)
        assertEquals(
            "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
            content.address,
        )
        assertEquals(2, content.amount.size)
        assertEquals("lovelace", content.amount[0].unit)
        assertEquals("42000000", content.amount[0].quantity)
        assertEquals(
            "b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e",
            content.amount[1].unit,
        )
        assertEquals("12", content.amount[1].quantity)
        assertEquals(false, content.script)
        assertEquals(
            "stake1ux3g2c9dx2nhhehyrezyxpkstartcqmu9hk63qgfkccw5rqttygt7",
            content.stakeAddress,
        )
        assertEquals("shelley", content.type)
    }
}
