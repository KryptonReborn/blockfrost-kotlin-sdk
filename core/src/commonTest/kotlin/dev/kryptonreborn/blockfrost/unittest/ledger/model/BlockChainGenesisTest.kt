package dev.kryptonreborn.blockfrost.unittest.ledger.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.ledger.model.BlockchainGenesis
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class BlockChainGenesisTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/blockchain_genesis.json").readText()
        val content = Json.decodeFromString<BlockchainGenesis>(json)
        assertEquals(0.05, content.activeSlotsCoefficient)
        assertEquals(5, content.updateQuorum)
        assertEquals("45000000000000000", content.maxLovelaceSupply)
        assertEquals(764824073, content.networkMagic)
        assertEquals(432000, content.epochLength)
        assertEquals(1506203091, content.systemStart)
        assertEquals(129600, content.slotsPerKesPeriod)
        assertEquals(1, content.slotLength)
        assertEquals(62, content.maxKesEvolutions)
        assertEquals(2160, content.securityParam)
    }
}
