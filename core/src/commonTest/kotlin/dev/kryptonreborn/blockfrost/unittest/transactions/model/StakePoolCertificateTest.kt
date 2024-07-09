package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.StakePoolCertificate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class StakePoolCertificateTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_stake_pool_certificate.json".parseFirstElementInArray<StakePoolCertificate>()

        assertEquals(0, content.certIndex)
        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
        assertEquals("0b5245f9934ec2151116fb8ec00f35fd00e0aa3b075c4ed12cce440f999d8233", content.vrfKey)
        assertEquals("5000000000", content.pledge)
        assertEquals(0.05, content.marginCost)
        assertEquals("340000000", content.fixedCost)
        assertEquals("stake1uxkptsa4lkr55jleztw43t37vgdn88l6ghclfwuxld2eykgpgvg3f", content.rewardAccount)
        assertEquals(listOf("stake1u98nnlkvkk23vtvf9273uq7cph5ww6u2yq2389psuqet90sv4xv9v"), content.owners)

        val metadata = content.metadata
        assertNotNull(metadata)
        assertEquals("https://stakenuts.com/mainnet.json", metadata.url)
        assertEquals("47c0c68cb57f4a5b4a87bad896fc274678e7aea98e200fa14a1cb40c0cab1d8c", metadata.hash)
        assertEquals("NUTS", metadata.ticker)
        assertEquals("Stake Nuts", metadata.name)
        assertEquals("The best pool ever", metadata.description)
        assertEquals("https://stakentus.com/", metadata.homepage)

        val relay = content.relays[0]
        assertEquals("4.4.4.4", relay.ipv4)
        assertEquals("https://stakenuts.com/mainnet.json", relay.ipv6)
        assertEquals("relay1.stakenuts.com", relay.dns)
        assertEquals("_relays._tcp.relays.stakenuts.com", relay.dnsSrv)
        assertEquals(3001, relay.port)

        assertEquals(210, content.activeEpoch)
    }
}
