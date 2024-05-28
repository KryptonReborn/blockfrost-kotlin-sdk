package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.const.Const.BASE_URL
import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.utils.withIO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HealthApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_API_ROOT = "/api/v0/"
        const val PATH_HEALTH = "/api/v0/health"
        const val PATH_HEALTH_CLOCK = "/api/v0/health/clock"
    }

    suspend fun getApiRoot(): ApiRoot =
        withIO {
            httpClient.get("$BASE_URL$PATH_API_ROOT").body()
        }

    suspend fun getHealth(): Health =
        withIO {
            httpClient.get("$BASE_URL$PATH_HEALTH").body()
        }

    suspend fun getCurrentBackendTime(): Clock =
        withIO {
            httpClient.get("$BASE_URL$PATH_HEALTH_CLOCK").body()
        }
}
