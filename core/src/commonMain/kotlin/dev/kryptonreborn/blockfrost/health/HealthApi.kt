package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

/**
 * The `HealthApi` class provides methods to interact with the health endpoints of the Blockfrost API.
 *
 * @property httpClient The HttpClient instance used to make HTTP requests.
 */
internal class HealthApi(
    private val httpClient: HttpClient,
) {
    companion object {
        const val PATH_API_ROOT = "/api/v0/"
        const val PATH_HEALTH = "/api/v0/health"
        const val PATH_HEALTH_CLOCK = "/api/v0/health/clock"
    }

    /**
     * Retrieves the root information of the API.
     *
     * @return An [ApiRoot] object containing the root information of the API.
     * @throws ClientException If the API returns a client error response.
     * @throws ServerException If the API returns a server error response.
     */
    suspend fun getApiRoot() =
        httpClient.fetchResource<ApiRoot>(
            PATH_API_ROOT,
        )

    /**
     * Checks the health status of the API.
     *
     * @return A [Health] object containing the health status of the API.
     * @throws ClientException If the API returns a client error response.
     * @throws ServerException If the API returns a server error response.
     */
    suspend fun getHealth() =
        httpClient.fetchResource<Health>(
            PATH_HEALTH,
        )

    /**
     * Retrieves the current backend time in UNIX time milliseconds.
     *
     * @return A [Clock] object containing the current backend time.
     * @throws ClientException If the API returns a client error response.
     * @throws ServerException If the API returns a server error response.
     */
    suspend fun getCurrentBackendTime() =
        httpClient.fetchResource<Clock>(
            PATH_HEALTH_CLOCK,
        )
}
