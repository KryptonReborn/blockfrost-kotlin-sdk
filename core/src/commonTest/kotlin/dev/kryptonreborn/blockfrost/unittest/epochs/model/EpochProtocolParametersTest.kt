package dev.kryptonreborn.blockfrost.unittest.epochs.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.epochs.model.EpochProtocolParameters
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpochProtocolParametersTest {
    @Test
    fun testDeserialization() {
        val json =
            Resource("src/commonTest/resources/model/protocol_parameters.json").readText()
        val content = Json.decodeFromString<EpochProtocolParameters>(json)
        assertEquals(225, content.epoch)
        assertEquals(44, content.minFeeA)
        assertEquals(155381, content.minFeeB)
        assertEquals(65536, content.maxBlockSize)
        assertEquals(16384, content.maxTxSize)
        assertEquals(1100, content.maxBlockHeaderSize)
        assertEquals("2000000", content.keyDeposit)
        assertEquals("500000000", content.poolDeposit)
        assertEquals(18, content.eMax)
        assertEquals(150, content.nOpt)
        assertEquals(0.3, content.a0)
        assertEquals(0.003, content.rho)
        assertEquals(0.2, content.tau)
        assertEquals(0.5, content.decentralisationParam)
        assertEquals(null, content.extraEntropy)
        assertEquals(2, content.protocolMajorVer)
        assertEquals(0, content.protocolMinorVer)
        assertEquals("1000000", content.minUtxo)
        assertEquals("340000000", content.minPoolCost)
        assertEquals(
            "1a3be38bcbb7911969283716ad7aa550250226b76a61fc51cc9a9a35d9276d81",
            content.nonce,
        )
        assertTrue(content.costModels is Map<String, JsonElement>)
        assertEquals(0.0577, content.priceMem)
        assertEquals(0.0000721, content.priceStep)
        assertEquals("10000000", content.maxTxExMem)
        assertEquals("10000000000", content.maxTxExSteps)
        assertEquals("50000000", content.maxBlockExMem)
        assertEquals("40000000000", content.maxBlockExSteps)
        assertEquals("5000", content.maxValSize)
        assertEquals(150, content.collateralPercent)
        assertEquals(3, content.maxCollateralInputs)
        assertEquals("34482", content.coinsPerUtxoSize)
    }
}
