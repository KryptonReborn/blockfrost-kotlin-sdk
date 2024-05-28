package dev.kryptonreborn.blockfrost.utils

import dev.kryptonreborn.blockfrost.base.ApiError

fun Exception.toErrorResponse(): ApiError {
    return this as? ApiError ?: ApiError(
        500,
        this.message ?: "Unknown error",
        "Internal Server Error",
    )
}
