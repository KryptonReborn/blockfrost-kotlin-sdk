package presentation.ui.result

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import presentation.ui.result.viewmodel.ResultEvent
import presentation.ui.result.viewmodel.ResultState

@Composable
fun ResultScreen(
    state: ResultState,
    events: (ResultEvent) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier =
            Modifier.fillMaxSize()
                .scrollable(
                    state = scrollState,
                    orientation = Orientation.Vertical,
                ),
    ) {
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(count = 1) {
            if (state.isLoading) {
                Text("Loading...")
            }
            Text(state.result)
        }
    }
}
