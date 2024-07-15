package presentation.ui.result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.ui.result.viewmodel.ResultEvent
import presentation.ui.result.viewmodel.ResultState

@Composable
fun ResultScreen(
    state: ResultState,
    events: (ResultEvent) -> Unit,
    goBack: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(text = "Result")
                },
                navigationIcon = {
                    IconButton(onClick = { goBack.invoke() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
            ) {
                items(count = 1) {
                    if (state.isLoading) {
                        Text("Loading...")
                    }
                    Text(state.result)
                }
            }
        },
    )
}
