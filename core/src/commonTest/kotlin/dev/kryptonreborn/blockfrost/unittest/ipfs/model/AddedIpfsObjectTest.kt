package dev.kryptonreborn.blockfrost.unittest.ipfs.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.ipfs.model.AddedIpfsObject
import kotlin.test.Test
import kotlin.test.assertEquals

class AddedIpfsObjectTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/added_ipfs_object.json".resourceToExpectedData<AddedIpfsObject>()

        assertEquals("README.md", content.name)
        assertEquals("QmZbHqiCxKEVX7QfijzJTkZiSi3WEVTcvANgNAWzDYgZDr", content.ipfsHash)
        assertEquals("125297", content.size)
    }
}
