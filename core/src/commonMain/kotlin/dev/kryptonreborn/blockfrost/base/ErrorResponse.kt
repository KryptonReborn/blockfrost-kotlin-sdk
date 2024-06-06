package dev.kryptonreborn.blockfrost.base

import dev.kryptonreborn.blockfrost.ktor.Ktor
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * The `ErrorResponse` class represents an error response from the Blockfrost API.
 *
 * @property statusCode The HTTP status code of the error response.
 * @property error The error message of the error response.
 * @property message The detailed message of the error response.
 */
@Serializable
internal data class ErrorResponse(
    @SerialName("status_code")
    val statusCode: Int? = null,
    private val error: String? = null,
    private val message: String? = null,
) {
    fun isClientError() = statusCode in 400..499

    fun isServerError() = statusCode in 500..599

    fun message(): String = message ?: "Unknown message"

    fun error() = error ?: "Unknown error"
}

internal inline fun <reified T> handleResponseFromString(jsonString: String): T {
    when (val jsonElement = Ktor.json.parseToJsonElement(jsonString)) {
        is JsonArray -> {
            return Ktor.json.decodeFromJsonElement(jsonElement)
        }

        is JsonObject -> {
            if (jsonElement.containsKey("status_code")) {
                val errorResponse = Ktor.json.decodeFromJsonElement<ErrorResponse>(jsonElement)
                throw getExceptionFromResponse(errorResponse)
            }
            return Ktor.json.decodeFromJsonElement(jsonElement)
        }

        else -> throw IllegalArgumentException("Unexpected JSON format")
    }
}

internal inline fun getExceptionFromResponse(res: ErrorResponse): BlockfrostException {
    return when {
        res.statusCode == HttpStatusCode.BadRequest.value ->
            BadRequestException(
                res.message(),
                res.error(),
            )

        res.statusCode == HttpStatusCode.Forbidden.value ->
            ForbiddenException(
                res.message(),
                res.error(),
            )

        res.statusCode == HttpStatusCode.NotFound.value ->
            NotFoundException(
                res.message(),
                res.error(),
            )

        res.statusCode == 418 -> BannedException(res.message(), res.error())

        res.statusCode == HttpStatusCode.TooManyRequests.value ->
            RateLimitedException(
                res.message(),
                res.error(),
            )

        res.isClientError() ->
            ClientException(
                res.statusCode!!,
                res.message(),
                res.error(),
            )

        res.isServerError() ->
            ServerException(
                res.statusCode!!,
                res.message(),
                res.error(),
            )

        else ->
            BlockfrostException(
                res.message(),
                res.error(),
            )
    }
}
