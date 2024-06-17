package dev.kryptonreborn.blockfrost.unittest.epochs.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.epochs.model.StakeInfo
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class StakeInfoTest {
    @Test
    fun testDeserialization() {
        val json = Resource("src/commonTest/resources/model/stake_info.json").readText()
        val content = Json.decodeFromString<StakeInfo>(json)
        assertEquals("stake1u9l5q5jwgelgagzyt6nuaasefgmn8pd25c8e9qpeprq0tdcp0e3uk", content.stakeAddress)
        assertEquals("pool1pu5jlj4q9w9jlxeu370a3c9myx47md5j5m2str0naunn2q3lkdy", content.poolId)
        assertEquals("4440295078", content.amount)
    }
}
