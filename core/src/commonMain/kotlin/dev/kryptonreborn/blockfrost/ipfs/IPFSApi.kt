package dev.kryptonreborn.blockfrost.ipfs

import dev.kryptonreborn.blockfrost.base.ErrorResponse
import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.base.getExceptionFromResponse
import dev.kryptonreborn.blockfrost.base.handleResponseFromString
import dev.kryptonreborn.blockfrost.ipfs.model.AddedIpfsObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObject
import dev.kryptonreborn.blockfrost.ipfs.model.PinnedObjectDetails
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

internal class IPFSApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_IPFS_GATEWAY = "/api/v0/ipfs/gateway/:IPFS_path"
        const val PATH_IPFS_PIN_ADD = "/api/v0/ipfs/pin/add/:IPFS_path"
        const val PATH_IPFS_PIN_LIST = "/api/v0/ipfs/pin/list"
        const val PATH_IPFS_PIN_DETAILS = "/api/v0/ipfs/pin/list/:IPFS_path"
        const val PATH_IPFS_PIN_REMOVE = "/api/v0/ipfs/pin/remove/:IPFS_path"
        const val PATH_IPFS_ADD = "/api/v0/ipfs/add"
    }

    suspend fun addFileToIPFS(
        bytes: ByteArray,
        fileName: String,
    ): AddedIpfsObject {
        val formData =
            formData {
                append(
                    "file",
                    bytes,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    },
                )
            }

        return handleResponseFromString<AddedIpfsObject>(
            httpClient.request {
                url {
                    encodedPath += PATH_IPFS_ADD
                }
                this.method = HttpMethod.Post
                setBody(
                    MultiPartFormDataContent(
                        formData,
                    ),
                )
            }.bodyAsText(),
        )
    }

    suspend fun relayToIPFSGateway(IPFSPath: String): ByteArray {
        val response = httpClient.request {
            url {
                encodedPath += PATH_IPFS_GATEWAY.replace(":IPFS_path", IPFSPath)
            }
            this.method = HttpMethod.Get
            header(HttpHeaders.Accept, ContentType.Application.OctetStream)
        }
        val textResponse = response.bodyAsText()
        if (response.status == HttpStatusCode.OK && !isValidJson(textResponse)) {
            return response.readBytes()
        } else {
            val jsonElement = Ktor.json.parseToJsonElement(textResponse) as JsonObject
            if (jsonElement.containsKey("status_code")) {
                val errorResponse = Ktor.json.decodeFromJsonElement<ErrorResponse>(jsonElement)
                throw getExceptionFromResponse(errorResponse)
            } else {
                throw IllegalArgumentException("Unexpected JSON format")
            }
        }
    }

    suspend fun pinIPFSObject(IPFSPath: String) =
        httpClient.fetchResource<PinnedObject>(
            PATH_IPFS_PIN_ADD.replace(":IPFS_path", IPFSPath),
            HttpMethod.Post,
            requestBody = emptyMap<String, Any>(),
        )

    suspend fun getListPinnedObjects(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<PinnedObjectDetails>>(
            PATH_IPFS_PIN_LIST,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getPinnedObjectDetails(IPFSPath: String) =
        httpClient.fetchResource<PinnedObjectDetails>(
            PATH_IPFS_PIN_DETAILS.replace(":IPFS_path", IPFSPath),
        )

    suspend fun removePinnedObject(IPFSPath: String) =
        httpClient.fetchResource<PinnedObject>(
            PATH_IPFS_PIN_REMOVE.replace(":IPFS_path", IPFSPath),
            HttpMethod.Post,
            requestBody = emptyMap<String, Any>(),
        )

    private fun isValidJson(input: String): Boolean {
        return try {
            val jsonElement = Json.parseToJsonElement(input)
            jsonElement is JsonObject || jsonElement is JsonArray
        } catch (ex: Exception) {
            false
        }
    }
}
