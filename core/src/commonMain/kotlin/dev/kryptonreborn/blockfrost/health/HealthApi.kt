package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

internal class HealthApi(
    private val httpClient: HttpClient,
) {
    companion object {
        const val PATH_API_ROOT = "/api/v0/"
        const val PATH_HEALTH = "/api/v0/health"
        const val PATH_HEALTH_CLOCK = "/api/v0/health/clock"
    }

    suspend fun getApiRoot() =
        httpClient.fetchResource<ApiRoot>(
            PATH_API_ROOT,
        )

    suspend fun getHealth() =
        httpClient.fetchResource<Health>(
            PATH_HEALTH,
        )

    suspend fun getCurrentBackendTime() =
        httpClient.fetchResource<Clock>(
            PATH_HEALTH_CLOCK,
        )
}
