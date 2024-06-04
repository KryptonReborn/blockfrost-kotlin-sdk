package dev.kryptonreborn.blockfrost.unittest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_STAKE_ADDRESS
import dev.kryptonreborn.blockfrost.accounts.model.Account
import dev.kryptonreborn.blockfrost.accounts.model.Delegation
import dev.kryptonreborn.blockfrost.accounts.model.Reward
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AccountApiTest {
    private val stakeAddress = "stake_address"

    // Api get Account
    @Test
    fun testApiAccountReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_200.json"
            val expectedResult = resource.resourceToExpectedData<Account>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_STAKE_ADDRESS.replace(":stake_address", stakeAddress)
                )
            val result = accountsApi.getAccount(stakeAddress)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountReturn200WithFailData() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_STAKE_ADDRESS.replace(":stake_address", stakeAddress),
                    HttpStatusCode.OK
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    accountsApi.getAccount(stakeAddress)
                }
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiAccountReturn400() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_STAKE_ADDRESS.replace(":stake_address", stakeAddress),
                    HttpStatusCode.BadRequest
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    accountsApi.getAccount(stakeAddress)
                }
            assertEquals("Backend did not understand your request.", exception.message)
        }

    // Api get Account Delegation
    @Test
    fun testApiAccountDelegationReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_delegation_history_200.json"
            val expectedResult = resource.resourceToExpectedData<List<Delegation>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    CardanoAccountsApi.PATH_ACCOUNTS_DELEGATIONS.replace(
                        ":stake_address",
                        stakeAddress
                    )
                )
            val result = accountsApi.getAccountDelegations(stakeAddress)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountDelegationReturn200WithFailData() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val accountsApi =
                createAccountApi(
                    resource,
                    CardanoAccountsApi.PATH_ACCOUNTS_DELEGATIONS.replace(
                        ":stake_address",
                        stakeAddress
                    ),
                    HttpStatusCode.OK
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    accountsApi.getAccountDelegations(stakeAddress)
                }
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiAccountDelegationReturn400() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val accountsApi =
                createAccountApi(
                    resource,
                    CardanoAccountsApi.PATH_ACCOUNTS_DELEGATIONS.replace(
                        ":stake_address",
                        stakeAddress
                    ),
                    HttpStatusCode.BadRequest
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    accountsApi.getAccountDelegations(stakeAddress)
                }
            assertEquals("Backend did not understand your request.", exception.message)
        }

    // Api get Account Rewards
    @Test
    fun testApiAccountRewardsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_rewards_200.json"
            val expectedResult = resource.resourceToExpectedData<List<Reward>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    CardanoAccountsApi.PATH_ACCOUNTS_REWARDS.replace(":stake_address", stakeAddress)
                )
            val result = accountsApi.getAccountRewards(stakeAddress)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountRewardsReturn200WithFailData() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val accountsApi =
                createAccountApi(
                    resource,
                    CardanoAccountsApi.PATH_ACCOUNTS_REWARDS.replace(
                        ":stake_address",
                        stakeAddress
                    ),
                    HttpStatusCode.OK
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    accountsApi.getAccountRewards(stakeAddress)
                }
            assertEquals("Backend did not understand your request.", exception.message)
        }

    @Test
    fun testApiAccountRewardsReturn400() =
        runTest {
            val resource = "src/commonTest/resources/test_data_errors_response.json"
            val accountsApi =
                createAccountApi(
                    resource,
                    CardanoAccountsApi.PATH_ACCOUNTS_REWARDS.replace(
                        ":stake_address",
                        stakeAddress
                    ),
                    HttpStatusCode.BadRequest
                )
            val exception =
                assertFailsWith<BlockfrostException> {
                    accountsApi.getAccountRewards(stakeAddress)
                }
            assertEquals("Backend did not understand your request.", exception.message)
        }


    private fun createAccountApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoAccountsApi(
        TestKtorClient.createMockHttpClient(
            path,
            Resource(resource).readText(),
            status
        )
    )
}