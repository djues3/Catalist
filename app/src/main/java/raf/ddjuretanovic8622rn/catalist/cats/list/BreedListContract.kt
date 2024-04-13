package raf.ddjuretanovic8622rn.catalist.cats.list

import raf.ddjuretanovic8622rn.catalist.cats.list.model.BreedUiModel

interface BreedListContract {
    data class BreedListState(
        val loading: Boolean = false,
        val breeds: List<BreedUiModel> = emptyList(),
        val query: String = "",
        var filteredBreeds: List<BreedUiModel> = emptyList(),
    )
    sealed class BreedListEvent {
        data class SearchQueryChanged(val query: String) : BreedListEvent()
    }
}