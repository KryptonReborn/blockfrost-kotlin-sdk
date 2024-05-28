package dev.kryptonreborn.blockfrost.di

import dev.kryptonreborn.blockfrost.health.HealthApi
import dev.kryptonreborn.blockfrost.health.HealthRepo
import dev.kryptonreborn.blockfrost.ktor.Ktor
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication
import org.koin.dsl.module

abstract class BlockFrostKoinComponent : KoinComponent {
    override fun getKoin(): Koin = BlockFrostKoin.koin
}

internal object BlockFrostKoin {
    private val koinApp =
        koinApplication {
            // declare used modules
            modules(coreModule)
        }

    val koin = koinApp.koin
}

internal val coreModule =
    module {
        single {
            Ktor.httpClient
        }
        single {
            HealthApi(get())
        }
        single {
            HealthRepo(get())
        }
    }
