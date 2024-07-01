package dev.kryptonreborn.blockfrost.unittest.network.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.network.model.NetworkInformation
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkInformationTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/model/network_information.json".resourceToExpectedData<NetworkInformation>()

        // Supply details
        val supply = content.supply
        assertEquals("45000000000000000", supply.max)
        assertEquals("32890715183299160", supply.total)
        assertEquals("32412601976210393", supply.circulating)
        assertEquals("125006953355", supply.locked)
        assertEquals("98635632000000", supply.treasury)
        assertEquals("46635632000000", supply.reserves)

        // Stake details
        val stake = content.stake
        assertEquals("23204950463991654", stake.live)
        assertEquals("22210233523456321", stake.active)
    }
}
