package dev.kryptonreborn.blockfrost.unittest.utilities.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.utilities.model.TxIn
import dev.kryptonreborn.blockfrost.utilities.model.TxOut
import dev.kryptonreborn.blockfrost.utilities.model.TxOutValue
import dev.kryptonreborn.blockfrost.utilities.model.UtxoTupleListSerializer
import dev.kryptonreborn.blockfrost.utilities.model.UtxoTupleSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class UtxoTupleListSerializerTest {
    private val json =
        Json { serializersModule = SerializersModule { contextual(UtxoTupleListSerializer) } }

    @Test
    fun testSerializeUtxo() {
        val resource = Resource("src/commonTest/resources/model/tx_in.json").readText()
        val txIn = TxIn("string", 0)
        val jsonElement = json.encodeToString(UtxoTupleSerializer, txIn)
        assertEquals(
            jsonElement.normalizeJsonString(),
            resource.normalizeJsonString(),
        )
    }

    @Test
    fun testSerializeAdditionalUtxo() {
        val resource = Resource("src/commonTest/resources/model/tx_out.json").readText()
        val txOut =
            TxOut(
                "string",
                TxOutValue(0, emptyMap()),
                "string",
                emptyMap(),
                emptyMap(),
            )
        val jsonElement = json.encodeToString(UtxoTupleSerializer, txOut)
        assertEquals(
            jsonElement.normalizeJsonString(),
            resource.normalizeJsonString(),
        )
    }

    @Test
    fun testDeserializeUtxo() {
        val resource = Resource("src/commonTest/resources/model/tx_in.json").readText()
        val utxo = json.decodeFromString(UtxoTupleSerializer, resource)
        assertTrue(utxo is TxIn)
        assertEquals("string", utxo.txId)
        assertEquals(0, utxo.index)
    }

    @Test
    fun testDeserializeAdditionalUtxo() {
        val resource = Resource("src/commonTest/resources/model/tx_out.json").readText()
        val additionalUtxo = json.decodeFromString(UtxoTupleSerializer, resource)
        assertTrue(additionalUtxo is TxOut)
        assertEquals("string", additionalUtxo.address)
        assertEquals(0, additionalUtxo.value.coins)
        assertEquals(emptyMap(), additionalUtxo.value.assets)
        assertEquals("string", additionalUtxo.datumHash)
        assertEquals(emptyMap(), additionalUtxo.datum)
        assertEquals(emptyMap(), additionalUtxo.script)
    }

    @Test
    fun testDeserializeUnknownType() {
        val jsonString = """{"unknownField":"unknownValue"}"""
        assertFailsWith<SerializationException> {
            json.decodeFromString(UtxoTupleSerializer, jsonString)
        }
    }

    private fun String.normalizeJsonString() = replace("\\s".toRegex(), "")
}
