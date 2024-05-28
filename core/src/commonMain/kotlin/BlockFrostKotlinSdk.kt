import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.di.BlockFrostKoinComponent
import dev.kryptonreborn.blockfrost.health.HealthRepo
import org.koin.core.component.inject

object BlockFrostKotlinSdk : BlockFrostKoinComponent() {
    var blockfrostConfig = BlockfrostConfig()
    private val healthRepo: HealthRepo by inject()

    suspend fun getApiRoot() = healthRepo.getApiRoot()

    suspend fun getHealth() = healthRepo.getHealth()

    suspend fun getCurrentBackendTime() = healthRepo.getCurrentBackendTime()

    fun initConfig(config: BlockfrostConfig) {
        blockfrostConfig = config
    }
}
