package dev.kryptonreborn.blockfrost.unittest.nutlink.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.nutlink.model.NutLinkAddressMetadata
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class NutLinkAddressMetadataTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/nutlink_address_metadata.json".resourceToExpectedData<NutLinkAddressMetadata>()

        assertEquals(
            "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
            content.address,
        )
        assertEquals("https://nut.link/metadata.json", content.metadataUrl)
        assertEquals(
            "6bf124f217d0e5a0a8adb1dbd8540e1334280d49ab861127868339f43b3948af",
            content.metadataHash,
        )
        assertEquals(emptyMap<String, Any>(), content.metadata)
    }

    @Test
    fun testGetMetadata() {
        val content =
            NutLinkAddressMetadata(
                "addr1qxqs59lphg8g6qndelq8xwqn60ag3aeyfcp33c2kdp46a09re5df3pzwwmyq946axfcejy5n4x0y99wqpgtp2gd0k09qsgy6pz",
                "https://nut.link/metadata.json",
                "6bf124f217d0e5a0a8adb1dbd8540e1334280d49ab861127868339f43b3948af",
                JsonObject(mapOf("key" to JsonPrimitive("value"))),
            )
        assertEquals(mapOf("key" to "value"), content.metadata)
    }
}
