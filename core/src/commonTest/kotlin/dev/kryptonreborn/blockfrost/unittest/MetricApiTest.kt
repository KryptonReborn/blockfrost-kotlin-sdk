package dev.kryptonreborn.blockfrost.unittest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import dev.kryptonreborn.blockfrost.metrics.MetricsApi
import dev.kryptonreborn.blockfrost.metrics.MetricsApi.Companion.PATH_METRICS
import dev.kryptonreborn.blockfrost.metrics.MetricsApi.Companion.PATH_METRIC_ENDPOINTS
import dev.kryptonreborn.blockfrost.metrics.model.Metric
import dev.kryptonreborn.blockfrost.metrics.model.MetricEndpoint
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class MetricApiTest {
    @Test
    fun testApiMetricsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_metrics_200.json"
            val expectedData = resource.resourceToExpectedData<List<Metric>>()
            val metricsApi =
                createMetricsApi(resource, PATH_METRICS)
            val result = metricsApi.getMetrics()
            assertEquals(result, expectedData)
        }

    @Test
    fun testApiMetricsReturn200WithFailData() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val metricsApi =
                createMetricsApi(resource, PATH_METRICS, HttpStatusCode.OK)
            val exception =
                assertFailsWith<BlockfrostException> {
                    metricsApi.getMetrics()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiMetricsReturn400() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val metricsApi =
                createMetricsApi(resource, PATH_METRICS, HttpStatusCode.BadRequest)
            val exception =
                assertFailsWith<BlockfrostException> {
                    metricsApi.getMetrics()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiMetricEndpointReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_metric_endpoints_200.json"
            val expectedData = resource.resourceToExpectedData<List<MetricEndpoint>>()
            val metricsApi =
                createMetricsApi(resource, PATH_METRIC_ENDPOINTS)
            val result = metricsApi.getMetricEndpoints()
            assertEquals(result, expectedData)
        }

    @Test
    fun testApiMetricEndpointReturn200WithFailData() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val metricsApi =
                createMetricsApi(resource, PATH_METRIC_ENDPOINTS, HttpStatusCode.OK)
            val exception =
                assertFailsWith<BlockfrostException> {
                    metricsApi.getMetricEndpoints()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiMetricEndpointReturn400() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val metricsApi =
                createMetricsApi(resource, PATH_METRIC_ENDPOINTS, HttpStatusCode.BadRequest)
            val exception =
                assertFailsWith<BlockfrostException> {
                    metricsApi.getMetricEndpoints()
                }
            assertTrue(exception is BadRequestException)
            assertEquals("Backend did not understand your request.", exception.message)
        }

    private fun createMetricsApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = MetricsApi(TestKtorClient.createMockHttpClient(path, Resource(resource).readText(), status))
}
