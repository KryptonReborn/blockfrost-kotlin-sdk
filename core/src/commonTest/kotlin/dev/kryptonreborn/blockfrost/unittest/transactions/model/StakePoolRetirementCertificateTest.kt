package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.StakePoolRetirementCertificate
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolRetirementCertificateTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_stake_pool_retirement_certificate.json"
                .parseFirstElementInArray<StakePoolRetirementCertificate>()

        assertEquals(0, content.certIndex)
        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
        assertEquals(216, content.retiringEpoch)
    }
}
