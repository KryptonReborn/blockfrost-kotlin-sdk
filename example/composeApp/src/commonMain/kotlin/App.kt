import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import common.Context
import dev.kryptonreborn.blockfrost.BlockfrostConfig
import dev.kryptonreborn.blockfrost.NetworkType
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import presentation.navigation.AppNavigation
import presentation.ui.home.HomeScreen
import presentation.ui.home.viewmodel.HomeViewModel
import presentation.ui.result.ResultScreen
import presentation.ui.result.viewmodel.ResultViewModel

@Composable
fun App(context: Context) {
    KoinApplication(application = {
        modules(appModule(context))
        BlockFrostKotlinSdk.initConfig(
            BlockfrostConfig(
                networkType = NetworkType.Preprod,
                projectId = "mainnet7fToxtolmPU20aln1LrH2brEJOwq4ZoJ",
            ),
        )
    }) {
        MaterialTheme {
            val navigator = rememberNavController()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.Home.route,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    composable(route = AppNavigation.Home.route) {
                        val homeViewModel: HomeViewModel = koinInject()
                        HomeScreen(
                            state = homeViewModel.state.value,
                            events = homeViewModel::onTriggerEvent,
                        ) {
                            navigator.navigate(AppNavigation.Result.route.plus("/$it"))
                        }
                    }
                    composable(route = AppNavigation.Result.route.plus("/{id}")) { backStackEntry ->
                        val resultViewModel: ResultViewModel = koinInject()
                        val argument = backStackEntry.arguments
                        argument?.getString("id")?.let { id ->
                            LaunchedEffect(id) {
                                resultViewModel.onReceiveId(id)
                            }
                        }

                        ResultScreen(
                            state = resultViewModel.state.value,
                            events = resultViewModel::onTriggerEvent,
                        )
                    }
                }
            }
        }
    }
}
