package dev.kryptonreborn.blockfrost.unittest.base.model

import dev.kryptonreborn.blockfrost.base.model.RedeemerPurpose
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class RedeemerPurposeTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun testSerialization() {
        val spend = json.encodeToString(RedeemerPurpose.SPEND)
        val mint = json.encodeToString(RedeemerPurpose.MINT)
        val cert = json.encodeToString(RedeemerPurpose.CERT)
        val reward = json.encodeToString(RedeemerPurpose.REWARD)

        assertEquals("\"spend\"", spend)
        assertEquals("\"mint\"", mint)
        assertEquals("\"cert\"", cert)
        assertEquals("\"reward\"", reward)
    }

    @Test
    fun testDeserialization() {
        val spend = json.decodeFromString<RedeemerPurpose>("\"spend\"")
        val mint = json.decodeFromString<RedeemerPurpose>("\"mint\"")
        val cert = json.decodeFromString<RedeemerPurpose>("\"cert\"")
        val reward = json.decodeFromString<RedeemerPurpose>("\"reward\"")

        assertEquals(RedeemerPurpose.SPEND, spend)
        assertEquals(RedeemerPurpose.MINT, mint)
        assertEquals(RedeemerPurpose.CERT, cert)
        assertEquals(RedeemerPurpose.REWARD, reward)
    }
}
