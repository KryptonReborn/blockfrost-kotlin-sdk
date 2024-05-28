package presentation.ui.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
    Column(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            Text("Loading...")
            return
        }
        Text(state.result)
    }
}
