package dev.kryptonreborn.blockfrost.unittest.mempool.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.mempool.model.MempoolTransactionDetails
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MempoolTransactionDetailsTest {
    @Test
    fun testDeserialization() {
        val json =
            Resource("src/commonTest/resources/model/mempool_transaction_details.json").readText()
        val content = Json.decodeFromString<MempoolTransactionDetails>(json)

        // Transaction details
        val tx = content.tx
        assertEquals("8f9a160aac6a32b12b1e71eb84f0455a94a01e99d0b3ef610b48d85f400d7d47", tx.hash)
        assertEquals("3758528", tx.outputAmount[0].quantity)
        assertEquals("lovelace", tx.outputAmount[0].unit)
        assertEquals("171482", tx.outputAmount[1].quantity)
        assertEquals(
            "c881c20e49dbaca3ff6cef365969354150983230c39520b917f5cf7c4e696b65",
            tx.outputAmount[1].unit,
        )
        assertEquals("241472", tx.fees)
        assertEquals("0", tx.deposit)
        assertEquals(1186, tx.size)
        assertNull(tx.invalidBefore)
        assertEquals("127309303", tx.invalidHereafter)
        assertEquals(2, tx.utxoCount)
        assertEquals(0, tx.withdrawalCount)
        assertEquals(0, tx.mirCertCount)
        assertEquals(0, tx.delegationCount)
        assertEquals(0, tx.stakeCertCount)
        assertEquals(0, tx.poolUpdateCount)
        assertEquals(0, tx.poolRetireCount)
        assertEquals(0, tx.assetMintOrBurnCount)
        assertEquals(1, tx.redeemerCount)
        assertTrue(tx.validContract)

        // Inputs
        val input1 = content.inputs[0]
        assertEquals(
            "addr1zxn9efv2f6w82hagxqtn62ju4m293tqvw0uhmdl64ch8uw6j2c79gy9l76sdg0xwhd7r0c0kna0tycz4y5s6mlenh8pq6s3z70",
            input1.address,
        )
        assertEquals(
            "1dce127e338ae24a9369283a7c0470ef4611d18783da12d3f0fca2f91b549768",
            input1.txHash,
        )
        assertEquals(0, input1.outputIndex)
        assertEquals(false, input1.collateral)
        assertEquals(false, input1.reference)

        val input2 = content.inputs[1]
        assertEquals(
            "addr1q98vmjmnxl2epge8fsetvy7hr867lkcqera8cc55scpm248r7vg0n8j87h2dxxsjdyc6h963tm0h5t2wn7z8jt05gawqjlctqr",
            input2.address,
        )
        assertEquals(
            "ef9aa4dec771f45763ad68e98d82524d1b8699e393d1f82f5edeb60a92e9c30b",
            input2.txHash,
        )
        assertEquals(0, input2.outputIndex)
        assertEquals(true, input2.collateral)
        assertEquals(false, input2.reference)

        // Outputs
        val output = content.outputs[0]
        assertEquals(
            "addr1qxj0wyast442agname474c3sqwhy7x42xs6urqnrkut0nepqj9gj60ers45phd7mhwjrm7tu295338jgymznzh4dfhgqk0sfhq",
            output.address,
        )
        assertEquals("3758528", output.amount[0].quantity)
        assertEquals("lovelace", output.amount[0].unit)
        assertEquals("171482", output.amount[1].quantity)
        assertEquals(
            "c881c20e49dbaca3ff6cef365969354150983230c39520b917f5cf7c4e696b65",
            output.amount[1].unit,
        )
        assertEquals(0, output.outputIndex)
        assertNull(output.dataHash)
        assertNull(output.inlineDatum)
        assertEquals(false, output.collateral)
        assertNull(output.referenceScriptHash)

        // Redeemers
        val redeemer = content.redeemers[0]
        assertEquals(0, redeemer.txIndex)
        assertEquals("spend", redeemer.purpose)
        assertEquals("27895", redeemer.unitMem)
        assertEquals("12682260", redeemer.unitSteps)
    }
}
