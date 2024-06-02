package presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class AppNavigation(
    val route: String,
    val arguments: List<NamedNavArgument>,
) {
    data object Home : AppNavigation(route = "Home", arguments = emptyList())

    data object Result : AppNavigation(
        route = "Result",
        arguments =
            listOf(
                navArgument("id") {
                    type = NavType.StringType
                },
            ),
    )
}
