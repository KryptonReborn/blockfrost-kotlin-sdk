import dev.kryptonreborn.blockfrost.Config
import dev.kryptonreborn.blockfrost.di.BlockFrostKoinComponent
import dev.kryptonreborn.blockfrost.health.HealthRepo
import org.koin.core.component.inject

object BlockFrostKotlinSdk : BlockFrostKoinComponent() {
    private val healthRepo: HealthRepo by inject()

    suspend fun getApiRoot() = healthRepo.getApiRoot()

    suspend fun getHealth() = healthRepo.getHealth()

    suspend fun getCurrentBackendTime() = healthRepo.getCurrentBackendTime()

    fun initConfig(projectId: String) {
        Config.projectId = projectId
    }
}
