package dev.kryptonreborn.blockfrost.unittest.blocks.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.blocks.model.BlockContent
import kotlin.test.Test
import kotlin.test.assertEquals

class BlockTest {
    @Test
    fun testDeserialization() {
        val block =
            "src/commonTest/resources/model/block.json".resourceToExpectedData<BlockContent>()
        assertEquals(1641338934, block.time)
        assertEquals(15243593, block.height)
        assertEquals("4ea1ba291e8eef538635a53e59fddba7810d1679631cc3aed7c8e6c4091a516a", block.hash)
        assertEquals(412162133, block.slot)
        assertEquals(425, block.epoch)
        assertEquals(12, block.epochSlot)
        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2qnikdy", block.slotLeader)
        assertEquals(1, block.txCount)
        assertEquals("128314491794", block.output)
        assertEquals("592661", block.fees)
        assertEquals(
            "vrf_vk1wf2k6lhujezqcfe00l6zetxpnmh9n6mwhpmhm0dvfh3fxgmdnrfqkms8ty",
            block.blockVrf,
        )
        assertEquals(
            "43ebccb3ac72c7cebd0d9b755a4b08412c9f5dcb81b8a0ad1e3c197d29d47b05",
            block.previousBlock,
        )
        assertEquals(
            "8367f026cf4b03e116ff8ee5daf149b55ba5a6ec6dec04803b8dc317721d15fa",
            block.nextBlock,
        )
        assertEquals(4698, block.confirmations)
        assertEquals(
            "da905277534faf75dae41732650568af545134ee08a3c0392dbefc8096ae177c",
            block.opCert,
        )
        assertEquals("18", block.opCertCounter)
        assertEquals(3, block.size)
    }
}
