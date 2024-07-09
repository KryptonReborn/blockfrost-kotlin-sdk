package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.DelegationCertificate
import kotlin.test.Test
import kotlin.test.assertEquals

class DelegationCertificateTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/list_delegation_certificates.json".parseFirstElementInArray<DelegationCertificate>()

        assertEquals(0, content.certIndex)
        assertEquals("stake1u9r76ypf5fskppa0cmttas05cgcswrttn6jrq4yd7jpdnvc7gt0yc", content.address)
        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
        assertEquals(210, content.activeEpoch)
    }
}
