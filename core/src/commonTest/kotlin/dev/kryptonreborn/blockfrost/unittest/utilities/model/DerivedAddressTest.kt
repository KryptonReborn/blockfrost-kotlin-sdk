package dev.kryptonreborn.blockfrost.unittest.utilities.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.utilities.model.DerivedAddress
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class DerivedAddressTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/derived_address.json").readText()
        val content = Json.decodeFromString<DerivedAddress>(json)
        assertEquals(
            "xpub",
            content.xpub,
        )
        assertEquals(0, content.role)
        assertEquals(0, content.index)
        assertEquals(
            "address",
            content.address,
        )
    }
}
