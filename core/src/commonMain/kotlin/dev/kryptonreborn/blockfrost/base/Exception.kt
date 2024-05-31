package dev.kryptonreborn.blockfrost.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BlockfrostException(
    override val message: String,
    open val error: String,
) : Exception(message)

open class ClientException(
    @SerialName("status_code")
    val statusCode: Int,
    override val message: String,
    override val error: String,
) : BlockfrostException(message, error)

class ServerException(
    @SerialName("status_code")
    val statusCode: Int,
    override val message: String,
    override val error: String,
) : BlockfrostException(message, error)

class BadRequestException(
    override val message: String,
    override val error: String,
) : ClientException(400, message, error)

class ForbiddenException(
    override val message: String,
    override val error: String,
) : ClientException(403, message, error)

class NotFoundException(
    override val message: String,
    override val error: String,
) : ClientException(404, message, error)

class BannedException(
    override val message: String,
    override val error: String,
) : ClientException(418, message, error)

class RateLimitedException(
    override val message: String,
    override val error: String,
) : ClientException(429, message, error)
