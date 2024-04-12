package raf.ddjuretanovic8622rn.catalist.cats.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.ddjuretanovic8622rn.catalist.cats.list.BreedListContract.BreedListState
import raf.ddjuretanovic8622rn.catalist.cats.list.BreedListContract.BreedListUiEvent

fun NavGraphBuilder.breeds(
    route: String,
    onBreedClick: (String) -> Unit,
) = composable(route = route) {
    val breedListViewModel = viewModel<BreedListViewModel>()
    val state = breedListViewModel.state.collectAsState()

    BreedListScreen(
        state = state.value, eventPublisher = {
            breedListViewModel.setEvent(it)
        }, onBreedClick = onBreedClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedListScreen(
    state: BreedListState,
    eventPublisher: (uiEvent: BreedListUiEvent) -> Unit,
    onBreedClick: (String) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Breeds") })
    }, content = { paddingValues ->
        if (state.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(modifier = Modifier.padding(vertical = 150.dp)) {
                    Text(
                        text = "Loading..."
                    )
                    CircularProgressIndicator()
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = paddingValues
            ) {
                items(items = state.breeds, key = { it.id }) { breed ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .padding(16.dp)
                            .clickable { onBreedClick(breed.id) },
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(vertical = 16.dp)
                                    .weight(weight = 1f),
                                text = "${breed.name}\n${breed.altNames}",
                                style = MaterialTheme.typography.headlineSmall,
                            )
                        }
                    }
                }
            }
        }
    })
}
