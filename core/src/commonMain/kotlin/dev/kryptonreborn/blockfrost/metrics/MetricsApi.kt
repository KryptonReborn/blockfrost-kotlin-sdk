package dev.kryptonreborn.blockfrost.metrics

import dev.kryptonreborn.blockfrost.ktor.fetchResource
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import io.ktor.client.HttpClient

internal class MetricsApi(
    private val httpClient: HttpClient,
) {
    companion object {
        const val PATH_METRICS = "/api/v0/metrics"
        const val PATH_METRIC_ENDPOINTS = "/api/v0/metrics/endpoints"
    }

    suspend fun getMetrics() =
        httpClient.fetchResource<List<Metric>>(
            PATH_METRICS,
        )

    suspend fun getMetricEndpoints() =
        httpClient.fetchResource<List<MetricEndpoint>>(
            PATH_METRIC_ENDPOINTS,
        )
}
