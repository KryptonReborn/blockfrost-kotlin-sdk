package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.transactions.model.TransactionUtxos
import kotlin.test.Test
import kotlin.test.assertEquals

class TransactionUtxosTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/model/transaction_utxos.json".resourceToExpectedData<TransactionUtxos>()

        assertEquals("1e043f100dce12d107f679685acd2fc0610e10f72a92d412794c9773d11d8477", content.hash)

        // Inputs
        val input = content.inputs[0]
        assertEquals(
            "addr1q9ld26v2lv8wvrxxmvg90pn8n8n5k6tdst06q2s856rwmvnueldzuuqmnsye359fqrk8hwvenjnqultn7djtrlft7jnq7dy7wv",
            input.address,
        )
        assertEquals("lovelace", input.amount[0].unit)
        assertEquals("42000000", input.amount[0].quantity)
        assertEquals("b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e", input.amount[1].unit)
        assertEquals("12", input.amount[1].quantity)
        assertEquals("1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dce628516157f0", input.txHash)
        assertEquals(0, input.outputIndex)
        assertEquals("9e478573ab81ea7a8e31891ce0648b81229f408d596a3483e6f4f9b92d3cf710", input.dataHash)
        assertEquals("19a6aa", input.inlineDatum)
        assertEquals("13a3efd825703a352a8f71f4e2758d08c28c564e8dfcce9f77776ad1", input.referenceScriptHash)
        assertEquals(false, input.collateral)
        assertEquals(false, input.reference)

        // Outputs
        val output = content.outputs[0]
        assertEquals(
            "addr1q9ld26v2lv8wvrxxmvg90pn8n8n5k6tdst06q2s856rwmvnueldzuuqmnsye359fqrk8hwvenjnqultn7djtrlft7jnq7dy7wv",
            output.address,
        )
        assertEquals("lovelace", output.amount[0].unit)
        assertEquals("42000000", output.amount[0].quantity)
        assertEquals("b0d07d45fe9514f80213f4020e5a61241458be626841cde717cb38a76e7574636f696e", output.amount[1].unit)
        assertEquals("12", output.amount[1].quantity)
        assertEquals(0, output.outputIndex)
        assertEquals("9e478573ab81ea7a8e31891ce0648b81229f408d596a3483e6f4f9b92d3cf710", output.dataHash)
        assertEquals("19a6aa", output.inlineDatum)
        assertEquals(false, output.collateral)
        assertEquals("13a3efd825703a352a8f71f4e2758d08c28c564e8dfcce9f77776ad1", output.referenceScriptHash)
    }
}
