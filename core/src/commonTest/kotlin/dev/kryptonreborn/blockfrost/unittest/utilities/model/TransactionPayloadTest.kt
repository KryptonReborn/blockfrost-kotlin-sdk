package dev.kryptonreborn.blockfrost.unittest.utilities.model

import com.goncalossilva.resources.Resource
import com.ionspin.kotlin.bignum.integer.BigInteger
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.utilities.model.TransactionPayload
import dev.kryptonreborn.blockfrost.utilities.model.TransactionInput
import dev.kryptonreborn.blockfrost.utilities.model.TransactionOutput
import dev.kryptonreborn.blockfrost.utilities.model.TransactionOutputValue
import dev.kryptonreborn.blockfrost.utilities.model.UTxO
import dev.kryptonreborn.blockfrost.utilities.model.toPayload
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TransactionPayloadTest {
    @Test
    fun testToPayload() {
        val json = Resource("src/commonTest/resources/model/transaction_payload.json").readText()
        val content =
            TransactionPayload(
                cbor = "string",
                uTxO = UTxO(
                    TransactionInput("string", 0),
                    TransactionOutput(
                        "string",
                        TransactionOutputValue(BigInteger(0), emptyMap()),
                        "string",
                        emptyMap(),
                        emptyMap(),
                    ),
                )
            )
        val payload = content.toPayload()
        val expected = Ktor.json.encodeToString(payload)
        assertEquals(json.replace("\\s", ""), expected.replace("\\s", ""))
    }
}
