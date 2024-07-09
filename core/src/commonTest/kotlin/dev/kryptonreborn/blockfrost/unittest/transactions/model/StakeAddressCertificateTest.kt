package dev.kryptonreborn.blockfrost.unittest.transactions.model

import dev.kryptonreborn.blockfrost.TestKtorClient.parseFirstElementInArray
import dev.kryptonreborn.blockfrost.transactions.model.StakeAddressCertificate
import kotlin.test.Test
import kotlin.test.assertEquals

class StakeAddressCertificateTest {
    @Test
    fun testDeserialization() {
        val content =
            "src/commonTest/resources/list_stake_address_certificates.json".parseFirstElementInArray<StakeAddressCertificate>()

        assertEquals(0, content.certIndex)
        assertEquals("stake1u9t3a0tcwune5xrnfjg4q7cpvjlgx9lcv0cuqf5mhfjwrvcwrulda", content.address)
        assertEquals(true, content.registration)
    }
}
