package dev.kryptonreborn.blockfrost.unittest.ipfs.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.ipfs.model.ObjectState
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObjectDetails
import kotlin.test.Test
import kotlin.test.assertEquals

class PinnedObjectDetailsTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_pinned_object_details.json".parseFirstElementInArray<PinnedObjectDetails>()

        assertEquals(1615551024, content.timeCreated)
        assertEquals(1615551024, content.timePinned)
        assertEquals("QmdVMnULrY95mth2XkwjxDtMHvzuzmvUPTotKE1tgqKbCx", content.ipfsHash)
        assertEquals("1615551024", content.size)
        assertEquals(ObjectState.Pinned, content.state)
    }
}