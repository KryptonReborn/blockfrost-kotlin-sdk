package presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "Health Api",
                style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
            )
            TextButton(onClick = {
                navigateToResult(ResultEvent.GetApiRoot.id)
            }) {
                Text("Get Api Root")
            }
            TextButton(onClick = {
                navigateToResult(ResultEvent.GetHealth.id)
            }) {
                Text("Get Api Health")
            }
            TextButton(onClick = {
                navigateToResult(ResultEvent.GetCurrentBackendTime.id)
            }) {
                Text("Get Current Backend Time")
            }
        }
    }
}
