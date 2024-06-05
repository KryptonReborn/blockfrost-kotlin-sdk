package dev.kryptonreborn.blockfrost.unittest

import com.goncalossilva.resources.Resource
import dev.kryptonreborn.blockfrost.TestKtorClient
import dev.kryptonreborn.blockfrost.TestKtorClient.resourceToExpectedData
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES_ASSETS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_ADDRESSES_TOTAL
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_DELEGATIONS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_HISTORY
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_MIRS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_REGISTRATIONS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_REWARDS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_STAKE_ADDRESS
import dev.kryptonreborn.blockfrost.accounts.CardanoAccountsApi.Companion.PATH_ACCOUNTS_WITHDRAWALS
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesAsset
import dev.kryptonreborn.blockfrost.accounts.model.AccountAddressesContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountContentTotal
import dev.kryptonreborn.blockfrost.accounts.model.AccountDelegationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountHistoryContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountMirContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountQueryParameters
import dev.kryptonreborn.blockfrost.accounts.model.AccountRegistrationContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountRewardContent
import dev.kryptonreborn.blockfrost.accounts.model.AccountWithdrawalContent
import dev.kryptonreborn.blockfrost.base.BadRequestException
import dev.kryptonreborn.blockfrost.base.BlockfrostException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class AccountApiTest {
    private val stakeAddress = "stake_address"
    private val queryParameters = AccountQueryParameters()

    @Test
    fun testApiAccountReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_200.json"
            val expectedResult = resource.resourceToExpectedData<AccountContent>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_STAKE_ADDRESS,
                )
            val result = accountsApi.getAccount(stakeAddress)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountDelegationReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_delegation_history_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountDelegationContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_DELEGATIONS,
                )
            val result = accountsApi.getAccountDelegations(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountRewardsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_rewards_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountRewardContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_REWARDS,
                )
            val result = accountsApi.getAccountRewards(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountHistoryReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_histories_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountHistoryContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_HISTORY,
                )
            val result = accountsApi.getAccountHistory(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountRegistrationsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_registrations_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountRegistrationContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_REGISTRATIONS,
                )
            val result = accountsApi.getAccountRegistrations(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountWithdrawalsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_withdrawals_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountWithdrawalContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_WITHDRAWALS,
                )
            val result = accountsApi.getAccountWithdrawals(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountMirsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_mirs_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountMirContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_MIRS,
                )
            val result = accountsApi.getAccountMirs(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountAddressesReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_addresses_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountAddressesContent>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_ADDRESSES,
                )
            val result = accountsApi.getAccountAddresses(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountAddressesAssetsReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_addresses_assets_200.json"
            val expectedResult = resource.resourceToExpectedData<List<AccountAddressesAsset>>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_ADDRESSES_ASSETS,
                )
            val result = accountsApi.getAccountAddressesAssets(stakeAddress, queryParameters)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiAccountAddressesTotalReturnCorrectData() =
        runTest {
            val resource = "src/commonTest/resources/api_account_addresses_total_200.json"
            val expectedResult = resource.resourceToExpectedData<AccountContentTotal>()
            val accountsApi =
                createAccountApi(
                    resource,
                    PATH_ACCOUNTS_ADDRESSES_TOTAL,
                )
            val result = accountsApi.getAccountAddressesTotal(stakeAddress)
            assertEquals(result, expectedResult)
        }

    @Test
    fun testApiGetAccountReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_STAKE_ADDRESS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccount(stakeAddress)
            }
        }

    @Test
    fun testApiGetAccountReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_STAKE_ADDRESS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccount(stakeAddress)
            }
        }

    @Test
    fun testApiGetAccountDelegationReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_DELEGATIONS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountDelegations(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountDelegationReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_DELEGATIONS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountDelegations(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountRewardsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_REWARDS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountRewards(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountRewardsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_REWARDS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountRewards(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountHistoryReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_HISTORY,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountHistory(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountHistoryReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_HISTORY,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountHistory(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountRegistrationsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_REGISTRATIONS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountRegistrations(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountRegistrationsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_REGISTRATIONS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountRegistrations(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountWithdrawalsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_WITHDRAWALS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountWithdrawals(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountWithdrawalsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_WITHDRAWALS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountWithdrawals(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountMirsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_MIRS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountMirs(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountMirsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_MIRS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountMirs(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountAddressesReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_ADDRESSES,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountAddresses(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountAddressesReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_ADDRESSES,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountAddresses(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountAddressesAssetsReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_ADDRESSES_ASSETS,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountAddressesAssets(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountAddressesAssetsReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_ADDRESSES_ASSETS,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountAddressesAssets(stakeAddress, queryParameters)
            }
        }

    @Test
    fun testApiGetAccountAddressesTotalReturn200WithFailData() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_ADDRESSES_TOTAL,
                HttpStatusCode.OK,
            ) { accountsApi ->
                accountsApi.getAccountAddressesTotal(stakeAddress)
            }
        }

    @Test
    fun testApiGetAccountAddressesTotalReturn400() =
        runTest {
            testApiWithBadRequest(
                PATH_ACCOUNTS_ADDRESSES_TOTAL,
                HttpStatusCode.BadRequest,
            ) { accountsApi ->
                accountsApi.getAccountAddressesTotal(stakeAddress)
            }
        }

    private fun createAccountApi(
        resource: String,
        path: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ) = CardanoAccountsApi(
        TestKtorClient.createMockHttpClient(
            path.replace(
                ":stake_address",
                stakeAddress,
            ),
            Resource(resource).readText(),
            status,
        ),
    )

    private suspend fun testApiWithBadRequest(
        path: String,
        statusCode: HttpStatusCode,
        apiCall: suspend (CardanoAccountsApi) -> Unit,
    ) {
        val accountsApi =
            createAccountApi(
                "src/commonTest/resources/test_data_errors_response.json",
                path,
                statusCode,
            )
        val exception = assertFailsWith<BlockfrostException> { apiCall(accountsApi) }
        assertTrue(exception is BadRequestException)
        assertEquals("Backend did not understand your request.", exception.message)
    }
}
