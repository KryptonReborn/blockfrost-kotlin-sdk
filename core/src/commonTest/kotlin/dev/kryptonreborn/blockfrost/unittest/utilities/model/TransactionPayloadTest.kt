package dev.kryptonreborn.blockfrost.unittest.utilities.model

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.utilities.model.TransactionInput
import dev.kryptonreborn.blockfrost.utilities.model.TransactionOutput
import dev.kryptonreborn.blockfrost.utilities.model.TransactionOutputValue
import dev.kryptonreborn.blockfrost.utilities.model.TransactionPayload
import dev.kryptonreborn.blockfrost.utilities.model.UTxO
import dev.kryptonreborn.blockfrost.utilities.model.toNormalizedPayload
import dev.kryptonreborn.blockfrost.utils.normalizeJsonString
import kotlin.test.Test
import kotlin.test.assertEquals

class TransactionPayloadTest {
    @Test
    fun testToPayload() {
        val json = Resource("src/commonTest/resources/model/transaction_payload.json").readText()
        val content =
            TransactionPayload(
                cbor = "cbor",
                uTxO =
                    UTxO(
                        TransactionInput(
                            "f9f2d84a12a1b0a3d68ef0c04b7d209fb9488b40d796f0c4cecc9155b67189b0",
                            0,
                        ),
                        TransactionOutput(
                            "addr_test1qpfn4z6j0y7kflf6rjnrhfzz27x4mpx39fgt80e08nlfth8xpvuvkjamflz5fttchsms2jpzpy602l3anedf2fd4n8xqzj4k4d",
                            TransactionOutputValue(10000000000000, mapOf("asset" to 10000000000000)),
                            "68720e1ba020fa18bfeb8862f62465b02d237d56e1de6df69e49f297d1a8afa8",
                            emptyMap(),
                            emptyMap(),
                        ),
                    ),
            )
        val payload = content.toNormalizedPayload()
        println(payload.normalizeJsonString())
        println(json.normalizeJsonString())
        assertEquals(json.normalizeJsonString(), payload.normalizeJsonString())
    }
}
