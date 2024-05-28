package dev.kryptonreborn.blockfrost.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("status_code")
    val statusCode: Int,
    override val message: String,
    val error: String,
) : Exception(message)

class ServerErrorException(override val message: String) : Exception(message)

object NetworkErrorException : Exception("Network error")

fun Exception.toErrorResponse(): ApiError {
    return this as? ApiError ?: ApiError(
        500,
        this.message ?: "Unknown error",
        "Internal Server Error",
    )
}
