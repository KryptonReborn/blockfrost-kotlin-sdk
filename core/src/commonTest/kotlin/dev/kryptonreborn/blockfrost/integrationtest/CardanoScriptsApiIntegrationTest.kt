package dev.kryptonreborn.blockfrost.integrationtest

import dev.kryptonreborn.blockfrost.scripts.model.Script
import dev.kryptonreborn.blockfrost.scripts.model.ScriptCbor
import dev.kryptonreborn.blockfrost.scripts.model.ScriptJson
import dev.kryptonreborn.blockfrost.scripts.model.ScriptRedeemer
import dev.kryptonreborn.blockfrost.scripts.model.SpecificScript
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class CardanoScriptsApiIntegrationTest : BaseIntegrationTest() {
    private val scriptHash = "65c197d565e88a20885e535f93755682444d3c02fd44dd70883fe89e"
    private val datumHash = "db583ad85881a96c73fbb26ab9e24d1120bb38f45385664bb9c797a2ea8d9a2d"

    @Test
    fun testGetScripts() =
        runIntegrationTest {
            val result = blockfrostClient.getScripts()
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<Script>)
        }

    @Test
    fun testGetScript() =
        runIntegrationTest {
            val result = blockfrostClient.getScript(scriptHash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is SpecificScript)
        }

    @Test
    fun testGetScriptJson() =
        runIntegrationTest {
            val result = blockfrostClient.getScriptJson(scriptHash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is ScriptJson)
        }

    @Test
    fun testGetScriptCbor() =
        runIntegrationTest {
            val result = blockfrostClient.getScriptCbor(scriptHash)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is ScriptCbor)
        }

    @Test
    fun testGetScriptRedeemers() =
        runIntegrationTest {
            val result = blockfrostClient.getScriptRedeemers(scriptHash)
            println(result)
            assertNotNull(result.getOrNull())
            assertTrue(result.getOrNull() is List<ScriptRedeemer>)
        }

    @Test
    fun testGetScriptDatum() =
        runIntegrationTest {
            val result = blockfrostClient.getScriptDatum(datumHash)
            assertNotNull(result.getOrNull())
        }

    @Test
    fun testGetScriptDatumCbor() =
        runIntegrationTest {
            val result = blockfrostClient.getScriptDatumCbor(datumHash)
            println(result)
            assertNotNull(result.getOrNull())
        }
}
