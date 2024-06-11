package dev.kryptonreborn.blockfrost.unittest.base

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BannedException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.base.ErrorResponse
import dev.kryptonreborn.blockfrost.base.ForbiddenException
import dev.kryptonreborn.blockfrost.base.NotFoundException
import dev.kryptonreborn.blockfrost.base.RateLimitedException
import dev.kryptonreborn.blockfrost.base.ServerException
import dev.kryptonreborn.blockfrost.base.getExceptionFromResponse
import dev.kryptonreborn.blockfrost.base.handleResponseFromString
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ErrorResponseTest {
    @Test
    fun testHandleErrorFromString() {
        val jsonString =
            Resource("src/commonTest/resources/test_data_errors_response.json").readText()
        val exception =
            assertFailsWith<BlockfrostException> {
                handleResponseFromString<AccountContent>(jsonString)
            }
        assertTrue(exception is BadRequestException)
    }

    @Test
    fun testGetExceptionFromResponse() {
        val error400Response = ErrorResponse(400, "error", "message")
        val exception400 = getExceptionFromResponse(error400Response)
        assertTrue(exception400 is BadRequestException)

        val error403Response = ErrorResponse(403, "error", "message")
        val exception403 = getExceptionFromResponse(error403Response)
        assertTrue(exception403 is ForbiddenException)

        val error404Response = ErrorResponse(404, "error", "message")
        val exception404 = getExceptionFromResponse(error404Response)
        assertTrue(exception404 is NotFoundException)

        val error418Response = ErrorResponse(418, "error", "message")
        val exception418 = getExceptionFromResponse(error418Response)
        assertTrue(exception418 is BannedException)

        val error429Response = ErrorResponse(429, "error", "message")
        val exception429 = getExceptionFromResponse(error429Response)
        assertTrue(exception429 is RateLimitedException)

        val error500Response = ErrorResponse(500, "error", "message")
        val exception500 = getExceptionFromResponse(error500Response)
        assertTrue(exception500 is ServerException)
    }
}
