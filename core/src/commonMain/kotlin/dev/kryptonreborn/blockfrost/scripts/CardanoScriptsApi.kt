package dev.kryptonreborn.blockfrost.scripts

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.scripts.model.DatumCborValue
import dev.kryptonreborn.blockfrost.scripts.model.DatumValue
import dev.kryptonreborn.blockfrost.scripts.model.Script
import dev.kryptonreborn.blockfrost.scripts.model.ScriptCbor
import dev.kryptonreborn.blockfrost.scripts.model.ScriptJson
import dev.kryptonreborn.blockfrost.scripts.model.ScriptRedeemer
import dev.kryptonreborn.blockfrost.scripts.model.SpecificScript
import io.ktor.client.HttpClient

internal class CardanoScriptsApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_GET_SCRIPTS = "/api/v0/scripts"
        const val PATH_GET_SCRIPT = "/api/v0/scripts/:script_hash"
        const val PATH_GET_SCRIPT_JSON = "/api/v0/scripts/:script_hash/json"
        const val PATH_GET_SCRIPT_CBOR = "/api/v0/scripts/:script_hash/cbor"
        const val PATH_GET_SCRIPT_REDEEMERS = "/api/v0/scripts/:script_hash/redeemers"
        const val PATH_GET_SCRIPT_DATUM = "/api/v0/scripts/datum/:datum_hash"
        const val PATH_GET_SCRIPT_DATUM_CBOR = "/api/v0/scripts/datum/:datum_hash/cbor"
    }

    suspend fun getScripts(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<Script>>(
            PATH_GET_SCRIPTS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getScript(scriptHash: String) =
        httpClient.fetchResource<SpecificScript>(
            PATH_GET_SCRIPT.replace(":script_hash", scriptHash),
        )

    suspend fun getScriptJson(scriptHash: String) =
        httpClient.fetchResource<ScriptJson>(
            PATH_GET_SCRIPT_JSON.replace(":script_hash", scriptHash),
        )

    suspend fun getScriptCbor(scriptHash: String) =
        httpClient.fetchResource<ScriptCbor>(
            PATH_GET_SCRIPT_CBOR.replace(":script_hash", scriptHash),
        )

    suspend fun getScriptRedeemers(
        scriptHash: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<ScriptRedeemer>>(
        PATH_GET_SCRIPT_REDEEMERS.replace(":script_hash", scriptHash),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getScriptDatum(datumHash: String) =
        httpClient.fetchResource<DatumValue>(
            PATH_GET_SCRIPT_DATUM.replace(":datum_hash", datumHash),
        )

    suspend fun getScriptDatumCbor(datumHash: String) =
        httpClient.fetchResource<DatumCborValue>(
            PATH_GET_SCRIPT_DATUM_CBOR.replace(":datum_hash", datumHash),
        )
}
