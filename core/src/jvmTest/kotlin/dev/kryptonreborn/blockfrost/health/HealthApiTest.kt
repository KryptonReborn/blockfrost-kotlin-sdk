package dev.kryptonreborn.blockfrost.health

import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.KtorClientTest
import dev.kryptonreborn.blockfrost.KtorClientTest.createMockEngine
import dev.kryptonreborn.blockfrost.base.ApiError
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode

class HealthApiTest : DescribeSpec({
    beforeTest {
        BlockFrostKotlinSdk.initConfig(BlockfrostConfig(projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ"))
    }
    describe("HealthApi") {
        context("getApiRoot") {
            it("returns 200 with success data") {
                val responseContent =
                    "{\n" +
                        "  \"url\": \"https://blockfrost.io/\",\n" +
                        "  \"version\": \"0.1.0\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine("/api/v0${HealthApi.PATH_API_ROOT}", responseContent)

                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val result = healthApi.getApiRoot()
                result.url shouldBe "https://blockfrost.io/"
                result.version shouldBe "0.1.0"
            }
            it("return 200 with bad request") {
                val responseContent =
                    "{\n" +
                        "  \"status_code\": 404,\n" +
                        "  \"error\": \"Not Found\",\n" +
                        "  \"message\": \"The requested resource could not be found.\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine(
                        "/api/v0${HealthApi.PATH_API_ROOT}",
                        responseContent,
                        HttpStatusCode.OK,
                    )
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val exception =
                    shouldThrow<ApiError> {
                        healthApi.getApiRoot()
                    }
                exception.message shouldBe "The requested resource could not be found."
            }
            it("return != 200 with bad request") {
                val responseContent =
                    "{\n" +
                        "  \"status_code\": 400,\n" +
                        "  \"error\": \"Bad Request\",\n" +
                        "  \"message\": \"Backend did not understand your request.\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine(
                        "/api/v0${HealthApi.PATH_API_ROOT}",
                        responseContent,
                        HttpStatusCode.NotFound,
                    )
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val exception =
                    shouldThrow<ApiError> {
                        healthApi.getApiRoot()
                    }
                exception.message shouldBe "Backend did not understand your request."
            }
        }

        context("getHealth") {
            it("returns 200 with success data") {
                val responseContent =
                    "{\n" +
                        "  \"is_healthy\": true\n" +
                        "}"
                val mockEngine =
                    createMockEngine("/api/v0${HealthApi.PATH_HEALTH}", responseContent)
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val result = healthApi.getHealth()
                result.isHealthy shouldBe true
            }

            it("returns 200 with bad request") {
                val responseContent =
                    "{\n" +
                        "  \"status_code\": 400,\n" +
                        "  \"error\": \"Bad Request\",\n" +
                        "  \"message\": \"Backend did not understand your request.\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine(
                        "/api/v0${HealthApi.PATH_HEALTH}",
                        responseContent,
                        HttpStatusCode.OK,
                    )
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val exception =
                    shouldThrow<ApiError> {
                        healthApi.getHealth()
                    }
                exception.message shouldBe "Backend did not understand your request."
            }
            it("returns !=200 with bad request") {
                val responseContent =
                    "{\n" +
                        "  \"status_code\": 502,\n" +
                        "  \"error\": \"Bad Request\",\n" +
                        "  \"message\": \"Backend did not understand your request.\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine(
                        "/api/v0${HealthApi.PATH_HEALTH}",
                        responseContent,
                        HttpStatusCode.BadGateway,
                    )
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val exception =
                    shouldThrow<ApiError> {
                        healthApi.getHealth()
                    }
                exception.message shouldBe "Backend did not understand your request."
            }
        }

        context("getCurrentBackendTime") {
            it("returns 200 with success data") {
                val responseContent =
                    "{\n" +
                        "  \"server_time\": 1620000000\n" +
                        "}"
                val mockEngine =
                    createMockEngine("/api/v0${HealthApi.PATH_HEALTH_CLOCK}", responseContent)
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val result = healthApi.getCurrentBackendTime()
                result.serverTime shouldBe 1620000000L
            }

            it("returns 200 with bad request") {
                val responseContent =
                    "{\n" +
                        "  \"status_code\": 400,\n" +
                        "  \"error\": \"Bad Request\",\n" +
                        "  \"message\": \"Backend did not understand your request.\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine(
                        "/api/v0${HealthApi.PATH_HEALTH_CLOCK}",
                        responseContent,
                        HttpStatusCode.OK,
                    )
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val exception =
                    shouldThrow<ApiError> {
                        healthApi.getCurrentBackendTime()
                    }
                exception.message shouldBe "Backend did not understand your request."
            }

            it("returns !=200 with bad request") {
                val responseContent =
                    "{\n" +
                        "  \"status_code\": 400,\n" +
                        "  \"error\": \"Bad Request\",\n" +
                        "  \"message\": \"Backend did not understand your request.\"\n" +
                        "}"
                val mockEngine =
                    createMockEngine(
                        "/api/v0${HealthApi.PATH_HEALTH_CLOCK}",
                        responseContent,
                        HttpStatusCode.BadRequest,
                    )
                val httpClient = KtorClientTest.httpClient(mockEngine)
                val healthApi = HealthApi(httpClient)
                val exception =
                    shouldThrow<ApiError> {
                        healthApi.getCurrentBackendTime()
                    }
                exception.message shouldBe "Backend did not understand your request."
            }
        }
    }
})
