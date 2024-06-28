package dev.kryptonreborn.blockfrost.unittest.base

import com.ionspin.kotlin.bignum.integer.BigInteger
import dev.kryptonreborn.blockfrost.base.BigIntegerSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.test.Test
import kotlin.test.assertEquals

class BigIntegerSerializerTest {
    private val json =
        Json {
            isLenient = true
            serializersModule =
                SerializersModule {
                    contextual(BigInteger::class, BigIntegerSerializer)
                }
        }

    @Test
    fun testSerialize() {
        val bigInteger = BigInteger(100000)
        val jsonString = json.encodeToString(bigInteger)
        assertEquals("\"100000\"", jsonString)
    }

    @Test
    fun testDeserialize() {
        val jsonString = "100000"
        val customObject = json.decodeFromString<BigInteger>(jsonString)
        assertEquals(BigInteger(100000), customObject)
    }
}
