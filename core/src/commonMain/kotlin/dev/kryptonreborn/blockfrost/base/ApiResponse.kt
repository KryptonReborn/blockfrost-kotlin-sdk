package dev.kryptonreborn.blockfrost.base

import dev.kryptonreborn.blockfrost.ktor.Ktor
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiResponse<T>(
    val data: T? = null,
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
    val apiResponse =
        try {
            Ktor.json.decodeFromString<ApiResponse<T>>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse()
        }
    if (apiResponse.statusCode != null && apiResponse.statusCode != 200) {
        handleErrorResponse(apiResponse)
    }
    return apiResponse.data ?: Ktor.json.decodeFromString<T>(jsonString)
        ?: throw IllegalStateException("No data provided")
}

internal inline fun <T> handleErrorResponse(res: ApiResponse<T>) {
    when {
        res.statusCode == HttpStatusCode.BadRequest.value -> {
            throw BadRequestException(
                res.message(),
                res.error(),
            )
        }

        res.statusCode == HttpStatusCode.Forbidden.value -> {
            throw ForbiddenException(
                res.message(),
                res.error(),
            )
        }

        res.statusCode == HttpStatusCode.NotFound.value -> {
            throw NotFoundException(
                res.message(),
                res.error(),
            )
        }

        res.statusCode == 418 -> {
            throw BannedException(res.message(), res.error())
        }

        res.statusCode == HttpStatusCode.TooManyRequests.value -> {
            throw RateLimitedException(
                res.message(),
                res.error(),
            )
        }

        res.isClientError() -> {
            throw ClientException(
                res.statusCode!!,
                res.message(),
                res.error(),
            )
        }

        res.isServerError() -> {
            throw ServerException(
                res.statusCode!!,
                res.message(),
                res.error(),
            )
        }
    }
}
