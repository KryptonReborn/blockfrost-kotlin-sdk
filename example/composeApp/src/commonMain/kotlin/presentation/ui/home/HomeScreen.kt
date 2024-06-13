package presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import presentation.ui.home.viewmodel.HomeEvent
import presentation.ui.home.viewmodel.HomeState
import presentation.ui.result.viewmodel.ResultEvent

@Composable
fun HomeScreen(
    state: HomeState,
    events: (HomeEvent) -> Unit,
    navigateToResult: (id: String) -> Unit,
) {
    Scaffold {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Health Api",
                    style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
                )
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetApiRoot.id) }) {
                    Text("Get Api Root")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetHealth.id) }) {
                    Text("Get Health")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetCurrentBackendTime.id) }) {
                    Text("Get Current Backend Time")
                }
            }
            item {
                Text(
                    text = "Metrics Api",
                    style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
                )
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetMetrics.id) }) {
                    Text("Get Metrics")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetMetricEndpoints.id) }) {
                    Text("Get Metric Endpoints")
                }
            }
            item {
                Text(
                    text = "Cardano Account Api",
                    style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
                )
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccount.id) }) {
                    Text("Get Account")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountAddressesAsset.id) }) {
                    Text("Get Account Addresses Asset")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountAddresses.id) }) {
                    Text("Get Account Addresses")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountAddressesTotal.id) }) {
                    Text("Get Account Addresses Total")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountDelegations.id) }) {
                    Text("Get Account Delegations")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountHistory.id) }) {
                    Text("Get Account History")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountMirs.id) }) {
                    Text("Get Account Mirs")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountRegistrations.id) }) {
                    Text("Get Account Registrations")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountRewards.id) }) {
                    Text("Get Account Rewards")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAccountWithdrawals.id) }) {
                    Text("Get Account Withdrawals")
                }
            }
            item {
                Text(
                    text = "Cardano Address Api",
                    style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
                )
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetSpecificAddress.id) }) {
                    Text("Get Specific Address")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetSpecificAddressExtended.id) }) {
                    Text("Get Specific Address Extended")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAddressDetail.id) }) {
                    Text("Get Address Detail")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAddressUtxos.id) }) {
                    Text("Get Address Utxos")
                }
            }
            item {
                Text(
                    text = "Assets Api",
                    style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
                )
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAssets.id) }) {
                    Text("Get Assets")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetSpecificAsset.id) }) {
                    Text("Get Specific Asset")
                }
            }
            item {
                TextButton(onClick = { navigateToResult(ResultEvent.GetAssetHistory.id) }) {
                    Text("Get Asset History")
                }
            }
        }
    }
}
