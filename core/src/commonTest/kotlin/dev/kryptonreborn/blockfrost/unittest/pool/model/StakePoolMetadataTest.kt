package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.pool.model.StakePoolMetadata
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolMetadataTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/model/stake_pool_metadata.json".resourceToExpectedData<StakePoolMetadata>()

        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
        assertEquals("0f292fcaa02b8b2f9b3c8f9fd8e0bb21abedb692a6d5058df3ef2735", content.hex)
        assertEquals("https://stakenuts.com/mainnet.json", content.url)
        assertEquals("47c0c68cb57f4a5b4a87bad896fc274678e7aea98e200fa14a1cb40c0cab1d8c", content.hash)
        assertEquals("NUTS", content.ticker)
        assertEquals("StakeNuts", content.name)
        assertEquals("StakeNuts.com", content.description)
        assertEquals("https://stakenuts.com/", content.homepage)
    }
}
