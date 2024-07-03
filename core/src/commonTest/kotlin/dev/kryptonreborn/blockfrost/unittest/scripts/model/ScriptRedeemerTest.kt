package dev.kryptonreborn.blockfrost.unittest.scripts.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.base.model.RedeemerPurpose
import dev.kryptonreborn.blockfrost.scripts.model.ScriptRedeemer
import kotlin.test.Test
import kotlin.test.assertEquals

class ScriptRedeemerTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_script_redeemer.json".parseFirstElementInArray<ScriptRedeemer>()

        assertEquals(
            "1a0570af966fb355a7160e4f82d5a80b8681b7955f5d44bec0dce628516157f0",
            content.txHash,
        )
        assertEquals(0, content.txIndex)
        assertEquals(RedeemerPurpose.SPEND, content.purpose)
        assertEquals(
            "923918e403bf43c34b4ef6b48eb2ee04babed17320d8d1b9ff9ad086e86f44ec",
            content.redeemerDataHash,
        )
        assertEquals("1700", content.unitMem)
        assertEquals("476468", content.unitSteps)
        assertEquals("172033", content.fee)
        assertEquals(null, content.datumHash)
    }
}
