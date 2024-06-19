package dev.kryptonreborn.blockfrost.unittest.utilities.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.utilities.model.TransactionPayload
import dev.kryptonreborn.blockfrost.utilities.model.TxIn
import dev.kryptonreborn.blockfrost.utilities.model.TxOut
import dev.kryptonreborn.blockfrost.utilities.model.TxOutValue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TransactionPayloadTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/evaluate_tx_request.json").readText()
        val content = Ktor.json.decodeFromString<TransactionPayload>(json)
        assertEquals("string", content.cbor)
        val utxo = content.additionalUtxoSet[0][0]
        assertTrue(utxo is TxIn)
        assertEquals("string", utxo.txId)
        assertEquals(0, utxo.index)
        val additionalUtxoSet = content.additionalUtxoSet[0][1]
        assertTrue(additionalUtxoSet is TxOut)
        assertEquals("string", additionalUtxoSet.address)
        assertEquals(0, additionalUtxoSet.value.coins)
        assertEquals(emptyMap(), additionalUtxoSet.value.assets)
        assertEquals("string", additionalUtxoSet.datumHash)
    }

    @Test
    fun testEncoding() {
        val content =
            TransactionPayload(
                "string",
                listOf(
                    listOf(
                        TxIn("string", 0),
                        TxOut(
                            "string",
                            TxOutValue(0, emptyMap()),
                            "string",
                            emptyMap(),
                            emptyMap(),
                        ),
                    ),
                ),
            )
        val json = Ktor.json.encodeToString(TransactionPayload.serializer(), content)
        println(json)
    }
}
