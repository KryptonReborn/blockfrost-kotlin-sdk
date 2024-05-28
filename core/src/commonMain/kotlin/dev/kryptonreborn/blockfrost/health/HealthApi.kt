package dev.kryptonreborn.blockfrost.health

import BlockFrostKotlinSdk.blockfrostConfig
import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.utils.withIO
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class HealthApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_API_ROOT = "/"
        const val PATH_HEALTH = "/health"
        const val PATH_HEALTH_CLOCK = "/health/clock"
    }

    suspend fun getApiRoot() =
        withIO {
            httpClient.fetchResource<ApiRoot>(
                "${blockfrostConfig.networkType.url}$PATH_API_ROOT",
            )
        }

    suspend fun getHealth() =
        withIO {
            httpClient.fetchResource<Health>(
                "${blockfrostConfig.networkType.url}$PATH_HEALTH",
            )
        }

    suspend fun getCurrentBackendTime() =
        withIO {
            httpClient.fetchResource<Clock>(
                "${blockfrostConfig.networkType.url}$PATH_HEALTH_CLOCK",
            )
        }
}
