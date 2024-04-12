package raf.ddjuretanovic8622rn.catalist.cats.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import raf.ddjuretanovic8622rn.catalist.cats.api.model.BreedsApiModel
import raf.ddjuretanovic8622rn.catalist.cats.list.BreedListContract.BreedListState
import raf.ddjuretanovic8622rn.catalist.cats.list.BreedListContract.BreedListUiEvent
import raf.ddjuretanovic8622rn.catalist.cats.list.model.BreedUiModel
import raf.ddjuretanovic8622rn.catalist.cats.repository.CatRepository
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class BreedListViewModel(
    private val repository: CatRepository = CatRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(BreedListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedListState.() -> BreedListState) = _state.update(reducer)
    private val events = MutableSharedFlow<BreedListUiEvent>()
    fun setEvent(event: BreedListUiEvent) = viewModelScope.launch { events.emit(event) }
    val TAG = "BreedListViewModel"

    init {
        fetchAll()
        observeEvents()
        observeSearchQuery()
    }

    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    is BreedListUiEvent.SearchQueryChanged -> {
                        setState { copy(query = it.query) }
                    }
                }
            }
        }
    }

    private fun fetchAll() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                delay(2.seconds)
                val breeds = withContext(Dispatchers.IO) {
                    repository.getBreeds().map { it.asBreedUiModel() }
                }
                setState { copy(breeds = breeds) }
                setState { copy(filteredBreeds = breeds) }
                Log.i(TAG, "fetchAll: Finished fetching data.\nSample: ${breeds.first()}")
            } catch (e: Exception) {
                Log.e(TAG, "fetchAll: Exception: $e")
            } finally {
                setState { copy(loading = false) }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            events.filterIsInstance<BreedListUiEvent.SearchQueryChanged>()
                .debounce(500.0.milliseconds).collect {
                    val filtered = state.value.breeds.filter {
                        it.name.contains(
                            state.value.query,
                            ignoreCase = true
                        ) || it.altNames.find { name ->
                            name.contains(
                                state.value.query,
                                ignoreCase = true
                            )
                        } != null
                    }
                    Log.i(TAG, "observeSearchQuery: Search query is: ${state.value.query}")
                    _state.value = _state.value.copy(filteredBreeds = filtered)
                }
        }
    }

    private fun BreedsApiModel.asBreedUiModel() = BreedUiModel(
        id = this.breedId,
        name = this.name,
        altNames = this.altNames.split(", "),
        description = this.description,
        temperament = this.temperament.split(", "),
    )
}