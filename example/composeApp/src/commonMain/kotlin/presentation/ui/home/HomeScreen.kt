package presentation.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import presentation.ui.home.viewmodel.HomeEvent
import presentation.ui.home.viewmodel.HomeState

@Composable
fun HomeScreen(
    state: HomeState,
    events: (HomeEvent) -> Unit,
    navigateToResult: (id: String) -> Unit,
) {
    Scaffold {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.items.size) { index ->
                val item = state.items.entries.elementAt(index)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = item.key,
                        style =
                            TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            ),
                    )
                    IconButton(onClick = {
                        events(HomeEvent.ToggleCardanoApi(item.key))
                    }) {
                        Text(text = if (item.value.show) "-" else "+")
                    }
                }
                if (item.value.show) {
                    item.value.items.forEach { api ->
                        TextButton(
                            onClick = { navigateToResult(api) },
                        ) {
                            Text(text = api)
                        }
                    }
                }
            }
        }
    }
}
