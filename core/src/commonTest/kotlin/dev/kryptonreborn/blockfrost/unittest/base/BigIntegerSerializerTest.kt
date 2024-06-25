package dev.kryptonreborn.blockfrost.unittest.base

import com.ionspin.kotlin.bignum.integer.BigInteger
import dev.kryptonreborn.blockfrost.ktor.Ktor
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

class BigIntegerSerializerTest {
    @Test
    fun testSerialize() {
        val bigInteger = BigInteger(100000)
        val jsonString = Ktor.json.encodeToString(bigInteger)
        assertEquals("\"100000\"", jsonString)
    }

    @Test
    fun testDeserialize() {
        val jsonString ="100000"
        val customObject = Ktor.json.decodeFromString<BigInteger>(jsonString)
        assertEquals(BigInteger(100000), customObject)
    }
}