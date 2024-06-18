package dev.kryptonreborn.blockfrost.epochs

import dev.kryptonreborn.blockfrost.base.QueryParameters
import dev.kryptonreborn.blockfrost.epochs.model.Epoch
import dev.kryptonreborn.blockfrost.epochs.model.EpochProtocolParameters
import dev.kryptonreborn.blockfrost.epochs.model.StakeInfo
import dev.kryptonreborn.blockfrost.ktor.fetchResource
import io.ktor.client.HttpClient

internal class CardanoEpochsApi(private val httpClient: HttpClient) {
    companion object {
        const val PATH_LATEST_EPOCH = "/api/v0/epochs/latest"
        const val PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS = "/api/v0/epochs/latest/parameters"
        const val PATH_SPECIFIC_EPOCH = "/api/v0/epochs/:number"
        const val PATH_LIST_NEXT_EPOCHS = "/api/v0/epochs/:number/next"
        const val PATH_LIST_PREVIOUS_EPOCHS = "/api/v0/epochs/:number/previous"
        const val PATH_STAKE_DISTRIBUTION = "/api/v0/epochs/:number/stakes"
        const val PATH_STAKE_DISTRIBUTION_POOL = "/api/v0/epochs/:number/stakes/:pool_id"
        const val PATH_BLOCK_DISTRIBUTION = "/api/v0/epochs/:number/blocks"
        const val PATH_BLOCK_DISTRIBUTION_POOL = "/api/v0/epochs/:number/blocks/:pool_id"
        const val PATH_PROTOCOL_PARAMETERS = "/api/v0/epochs/:number/parameters"
    }

    suspend fun getLatestEpoch() = httpClient.fetchResource<Epoch>(PATH_LATEST_EPOCH)

    suspend fun getLatestEpochProtocolParameters() =
        httpClient.fetchResource<EpochProtocolParameters>(PATH_LATEST_EPOCH_PROTOCOL_PARAMETERS)

    suspend fun getSpecificEpoch(number: Int) = httpClient.fetchResource<Epoch>(PATH_SPECIFIC_EPOCH.replace(":number", number.toString()))

    suspend fun getListNextEpochs(
        number: Int,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<Epoch>>(
        PATH_LIST_NEXT_EPOCHS.replace(
            ":number",
            number.toString(),
        ),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getListPreviousEpochs(
        number: Int,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<Epoch>>(
        PATH_LIST_PREVIOUS_EPOCHS.replace(
            ":number",
            number.toString(),
        ),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getStakeDistribution(
        number: Int,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<StakeInfo>>(
        PATH_STAKE_DISTRIBUTION.replace(
            ":number",
            number.toString(),
        ),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getStakeDistributionPool(
        number: Int,
        poolId: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<StakeInfo>>(
        PATH_STAKE_DISTRIBUTION_POOL.replace(
            ":number",
            number.toString(),
        ).replace(
            ":pool_id",
            poolId,
        ),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getBlockDistribution(
        number: Int,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<String>>(
        PATH_BLOCK_DISTRIBUTION.replace(
            ":number",
            number.toString(),
        ),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getBlockDistributionPool(
        number: Int,
        poolId: String,
        queryParameters: QueryParameters,
    ) = httpClient.fetchResource<List<String>>(
        PATH_BLOCK_DISTRIBUTION_POOL.replace(
            ":number",
            number.toString(),
        ).replace(
            ":pool_id",
            poolId,
        ),
        queryParams = queryParameters.toMap(),
    )

    suspend fun getProtocolParameters(number: Int) =
        httpClient.fetchResource<EpochProtocolParameters>(
            PATH_PROTOCOL_PARAMETERS.replace(
                ":number",
                number.toString(),
            ),
        )
}
