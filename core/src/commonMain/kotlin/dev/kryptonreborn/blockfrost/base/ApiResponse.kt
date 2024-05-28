package dev.kryptonreborn.blockfrost.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T? = null,
    @SerialName("status_code")
    val statusCode: Int? = null,
    val error: String? = null,
    val message: String? = null,
)
