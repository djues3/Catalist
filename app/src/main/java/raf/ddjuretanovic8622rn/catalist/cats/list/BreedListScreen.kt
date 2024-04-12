package raf.ddjuretanovic8622rn.catalist.cats.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.ddjuretanovic8622rn.catalist.cats.list.BreedListContract.BreedListState
import raf.ddjuretanovic8622rn.catalist.cats.list.BreedListContract.BreedListUiEvent


@OptIn(ExperimentalMaterial3Api::class)
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

@ExperimentalMaterial3Api
@Composable
fun BreedListScreen(
    state: BreedListState,
    eventPublisher: (uiEvent: BreedListUiEvent) -> Unit,
    onBreedClick: (String) -> Unit
) {
    Scaffold(content = { paddingValues ->
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
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
            ) {
                SearchView(eventPublisher)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), contentPadding = paddingValues
                ) {
                    items(items = state.filteredBreeds, key = { it.id }) { breed ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(16.dp)
                                .clickable { onBreedClick(breed.id) }
                                .fillMaxWidth()
                                .fillMaxHeight(),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = breed.name,
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                if (breed.altNames.isNotEmpty()) {
                                    Text(
                                        text = breed.altNames.joinToString(", "),
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(bottom = 8.dp),
                                    )
                                }
                                Text(
                                    text = if (breed.description.length > 250) {
                                        var i = 246
                                        while (breed.description[i].isLetter() || breed.description[i] == ' ' && i > 230) {
                                            i--
                                        }
                                        breed.description.substring(0, i) + "..."
                                    } else {
                                        breed.description
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                                    breed.temperament.shuffled().take(3).forEach {
                                        SuggestionChip(
                                            label = { Text(text = it) },
                                            onClick = {},
                                            modifier = Modifier.padding(horizontal = 8.dp)
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    })
}

@Composable
fun SearchView(
    eventPublisher: (uiEvent: BreedListUiEvent) -> Unit,
) {
    val searchQuery = remember { mutableStateOf("") }
    OutlinedTextField(value = searchQuery.value, onValueChange = {
        searchQuery.value = it
        eventPublisher(BreedListUiEvent.SearchQueryChanged(it))
    }, label = {
        Text(
            text = "Search",
        )
    }, modifier = Modifier.fillMaxWidth(), singleLine = true)
}