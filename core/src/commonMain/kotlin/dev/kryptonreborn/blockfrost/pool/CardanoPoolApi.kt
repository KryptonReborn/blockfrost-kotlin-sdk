package dev.kryptonreborn.blockfrost.pool

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.pool.model.StakePool
import dev.kryptonreborn.blockfrost.pool.model.StakePoolDelegator
import dev.kryptonreborn.blockfrost.pool.model.StakePoolHistory
import dev.kryptonreborn.blockfrost.pool.model.StakePoolInfo
import dev.kryptonreborn.blockfrost.pool.model.StakePoolMetadata
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRelay
import dev.kryptonreborn.blockfrost.pool.model.StakePoolRetire
import dev.kryptonreborn.blockfrost.pool.model.StakePoolUpdate
import io.ktor.client.HttpClient

internal class CardanoPoolApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_LIST_STAKE_POOLS = "/api/v0/pools"
        const val PATH_LIST_STAKE_POOLS_EXTENDED = "/api/v0/pools/extended"
        const val PATH_LIST_RETIRED_STAKE_POOLS = "/api/v0/pools/retired"
        const val PATH_LIST_RETIRING_STAKE_POOLS = "/api/v0/pools/retiring"
        const val PATH_SPECIFIC_STAKE_POOL = "/api/v0/pools/:pool_id"
        const val PATH_STAKE_POOL_HISTORY = "/api/v0/pools/:pool_id/history"
        const val PATH_STAKE_POOL_METADATA = "/api/v0/pools/:pool_id/metadata"
        const val PATH_STAKE_POOL_RELAYS = "/api/v0/pools/:pool_id/relays"
        const val PATH_LIST_STAKE_POOL_DELEGATORS = "/api/v0/pools/:pool_id/delegators"
        const val PATH_LIST_STAKE_POOL_BLOCKS = "/api/v0/pools/:pool_id/blocks"
        const val PATH_LIST_STAKE_POOL_UPDATES = "/api/v0/pools/:pool_id/updates"
    }

    suspend fun getListStakePools(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<String>>(
            PATH_LIST_STAKE_POOLS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getListStakePoolsExtended(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<StakePool>>(
            PATH_LIST_STAKE_POOLS_EXTENDED,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getListRetiredStakePools(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<StakePoolRetire>>(
            PATH_LIST_RETIRED_STAKE_POOLS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getListRetiringStakePools(queryParameters: QueryParameters) =
        httpClient.fetchResource<List<StakePoolRetire>>(
            PATH_LIST_RETIRING_STAKE_POOLS,
            queryParams = queryParameters.toMap(),
        )

    suspend fun getSpecificStakePool(poolId: String) =
        httpClient.fetchResource<StakePoolInfo>(
            PATH_SPECIFIC_STAKE_POOL.replace(":pool_id", poolId),
        )

    suspend fun getStakePoolHistory(
        poolId: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<StakePoolHistory>>(
        PATH_STAKE_POOL_HISTORY.replace(":pool_id", poolId),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getStakePoolMetadata(poolId: String) =
        httpClient.fetchResource<StakePoolMetadata>(
            PATH_STAKE_POOL_METADATA.replace(":pool_id", poolId),
        )

    suspend fun getStakePoolRelays(poolId: String) =
        httpClient.fetchResource<List<StakePoolRelay>>(
            PATH_STAKE_POOL_RELAYS.replace(":pool_id", poolId),
        )

    suspend fun getListStakePoolDelegators(
        poolId: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<StakePoolDelegator>>(
        PATH_LIST_STAKE_POOL_DELEGATORS.replace(":pool_id", poolId),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getListStakePoolBlocks(
        poolId: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<String>>(
        PATH_LIST_STAKE_POOL_BLOCKS.replace(":pool_id", poolId),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getListStakePoolUpdates(
        poolId: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<StakePoolUpdate>>(
        PATH_LIST_STAKE_POOL_UPDATES.replace(":pool_id", poolId),
        queryParams = queryParameters.toMap(),
    )
}
