package dev.kryptonreborn.blockfrost.unittest.blocks.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.blocks.model.BlockAddress
import kotlin.test.Test
import kotlin.test.assertEquals

class BlockAddressTest {
    @Test
    fun testDeserialization() {
        val blockAddress =
            "src/commonTest/resources/model/block_address.json".resourceToExpectedData<BlockAddress>()
        assertEquals(
            "addr1q9ld26v2lv8wvrxxmvg90pn8n8n5k6tdst06q2s856rwmvnueldzuuqmnsye359fqrk8hwvenjnqultn7djtrlft7jnq7dy7wv",
            blockAddress.address,
        )
        assertEquals(1, blockAddress.transactions.size)
        assertEquals(
            "1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dce628516157f0",
            blockAddress.transactions[0].txHash,
        )
    }
}
