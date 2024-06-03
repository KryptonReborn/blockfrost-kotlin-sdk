import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor
import dev.kryptonreborn.blockfrost.metrics.MetricsApi

class BlockFrostClient(blockfrostConfig: BlockfrostConfig) {
    private val httpClient by lazy { Ktor.httpClient(blockfrostConfig) }
    private val healthApi: HealthApi = HealthApi(httpClient)
    private val metricsApi: MetricsApi = MetricsApi(httpClient)

    // Health API
    suspend fun getApiRoot() = handleApiResult { healthApi.getApiRoot() }

    suspend fun getHealth() = handleApiResult { healthApi.getHealth() }

    suspend fun getCurrentBackendTime() = handleApiResult { healthApi.getCurrentBackendTime() }

    // Metrics API
    suspend fun getMetrics() = handleApiResult { metricsApi.getMetrics() }

    suspend fun getMetricEndpoints() = handleApiResult { metricsApi.getMetricEndpoints() }

    private inline fun <T> handleApiResult(block: () -> T): Result<T> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
