package raf.ddjuretanovic8622rn.catalist.cats.list

import raf.ddjuretanovic8622rn.catalist.cats.list.model.BreedUiModel

interface BreedListContract {
    data class BreedListState(
        val loading: Boolean = false,
        val breeds: List<BreedUiModel> = emptyList(),
        val query: String = "",
        var filteredBreeds: List<BreedUiModel> = emptyList(),
        val isSearchMode: Boolean = false,
    )
    sealed class BreedListUiEvent {
        data class SearchQueryChanged(val query: String) : BreedListUiEvent()
        data object ClearSearch : BreedListUiEvent()
        data object CloseSearchMode : BreedListUiEvent()
    }
}