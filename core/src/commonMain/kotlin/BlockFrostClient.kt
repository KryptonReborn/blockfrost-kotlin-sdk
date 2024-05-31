import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor

class BlockFrostClient(blockfrostConfig: BlockfrostConfig) {
    private val healthApi: HealthApi =
        HealthApi(Ktor.httpClient(blockfrostConfig.projectId), blockfrostConfig)

    suspend fun getApiRoot() = handleApiResult { healthApi.getApiRoot() }

    suspend fun getHealth() = handleApiResult { healthApi.getHealth() }

    suspend fun getCurrentBackendTime() = handleApiResult { healthApi.getCurrentBackendTime() }

    private inline fun <T> handleApiResult(block: () -> T): Result<T> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
