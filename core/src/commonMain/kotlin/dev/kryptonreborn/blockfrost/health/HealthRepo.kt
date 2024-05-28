package dev.kryptonreborn.blockfrost.health

import arrow.core.Either
import dev.kryptonreborn.blockfrost.health.model.ApiRoot
import dev.kryptonreborn.blockfrost.health.model.Clock
import dev.kryptonreborn.blockfrost.health.model.Health
import dev.kryptonreborn.blockfrost.utils.toErrorResponse

internal class HealthRepo(private val healthApi: HealthApi) {
    suspend fun getApiRoot() =
        Either.catchOrThrow<Exception, ApiRoot> {
            healthApi.getApiRoot()
        }.mapLeft { e ->
            e.toErrorResponse()
        }

    suspend fun getHealth() =
        Either.catchOrThrow<Exception, Health> {
            healthApi.getHealth()
        }.mapLeft { e ->
            e.toErrorResponse()
        }

    suspend fun getCurrentBackendTime() =
        Either.catchOrThrow<Exception, Clock> {
            healthApi.getCurrentBackendTime()
        }.mapLeft { e ->
            e.toErrorResponse()
        }
}
