package dev.kryptonreborn.blockfrost.metrics

import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import io.ktor.client.HttpClient

/**
 * The `MetricsApi` class provides methods to interact with the metrics endpoints of the Blockfrost API.
 *
 * @property httpClient The HttpClient instance used to make HTTP requests.
 */
internal class MetricsApi(
    private val httpClient: HttpClient,
) {
    companion object {
        const val PATH_METRICS = "/api/v0/metrics"
        const val PATH_METRIC_ENDPOINTS = "/api/v0/metrics/endpoints"
    }

    /**
     * Retrieves the history of your Blockfrost usage metrics for the past 30 days.
     *
     * @return A list of [Metric] objects containing the last 30 days of metrics.
     * @throws ClientException If the API returns a client error response.
     * @throws ServerException If the API returns a server error response.
     */
    suspend fun getMetrics() =
        httpClient.fetchResource<List<Metric>>(
            PATH_METRICS,
        )

    /**
     * Retrieves the history of your Blockfrost usage metrics per endpoint for the past 30 days.
     *
     * @return A list of [MetricEndpoint] objects containing the last 30 days of metrics per endpoint.
     * @throws ClientException If the API returns a client error response.
     * @throws ServerException If the API returns a server error response.
     */
    suspend fun getMetricEndpoints() =
        httpClient.fetchResource<List<MetricEndpoint>>(
            PATH_METRIC_ENDPOINTS,
        )
}
