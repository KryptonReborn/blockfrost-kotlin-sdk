package dev.kryptonreborn.blockfrost.unittest.pool.model

import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.pool.model.StakePoolInfo
import kotlin.test.Test
import kotlin.test.assertEquals

class StakePoolInfoTest {
    @Test
    fun testDeserialization() {
        val content = "src/commonTest/resources/model/specific_stake_pool.json".resourceToExpectedData<StakePoolInfo>()

        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
        assertEquals("0f292fcaa02b8b2f9b3c8f9fd8e0bb21abedb692a6d5058df3ef2735", content.hex)
        assertEquals("0b5245f9934ec2151116fb8ec00f35fd00e0aa3b075c4ed12cce440f999d8233", content.vrfKey)
        assertEquals(69, content.blocksMinted)
        assertEquals(4, content.blocksEpoch)
        assertEquals("6900000000", content.liveStake)
        assertEquals(0.42, content.liveSize)
        assertEquals(0.93, content.liveSaturation)
        assertEquals(127, content.liveDelegators)
        assertEquals("4200000000", content.activeStake)
        assertEquals(0.43, content.activeSize)
        assertEquals("5000000000", content.declaredPledge)
        assertEquals("5000000001", content.livePledge)
        assertEquals(0.05, content.marginCost)
        assertEquals("340000000", content.fixedCost)
        assertEquals("stake1uxkptsa4lkr55jleztw43t37vgdn88l6ghclfwuxld2eykgpgvg3f", content.rewardAccount)
        assertEquals(listOf("stake1u98nnlkvkk23vtvf9273uq7cph5ww6u2yq2389psuqet90sv4xv9v"), content.owners)
        assertEquals(
            listOf(
                "9f83e5484f543e05b52e99988272a31da373f3aab4c064c76db96643a355d9dc",
                "7ce3b8c433bf401a190d58c8c483d8e3564dfd29ae8633c8b1b3e6c814403e95",
                "3e6e1200ce92977c3fe5996bd4d7d7e192bcb7e231bc762f9f240c76766535b9",
            ),
            content.registration,
        )
        assertEquals(
            listOf("252f622976d39e646815db75a77289cf16df4ad2b287dd8e3a889ce14c13d1a8"),
            content.retirement,
        )
    }
}
