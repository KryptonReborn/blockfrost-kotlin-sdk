import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.ktor.Ktor

object BlockFrostKotlinSdk {
    lateinit var blockfrostConfig: BlockfrostConfig
    private val healthApi: HealthApi by lazy {
        if (!this::blockfrostConfig.isInitialized) {
            throw IllegalStateException("BlockFrostKotlinSdk is not initialized. Please call initConfig first.")
        }
        HealthApi(Ktor.httpClient)
    }

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

    fun initConfig(config: BlockfrostConfig) {
        blockfrostConfig = config
    }
}
