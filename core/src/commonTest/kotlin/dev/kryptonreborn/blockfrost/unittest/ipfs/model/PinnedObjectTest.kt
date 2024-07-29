package dev.kryptonreborn.blockfrost.unittest.ipfs.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.ipfs.model.ObjectState
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObject
import kotlin.test.Test
import kotlin.test.assertEquals

class PinnedObjectTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/pinned_object.json".resourceToExpectedData<PinnedObject>()

        assertEquals("QmPojRfAXYAXV92Dof7gtSgaVuxEk64xx9CKvprqu9VwA8", content.ipfsHash)
        assertEquals(ObjectState.Queued, content.state)
    }
}
