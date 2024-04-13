package raf.ddjuretanovic8622rn.catalist.cats.detail

import raf.ddjuretanovic8622rn.catalist.cats.detail.model.BreedDetailUIModel

interface BreedDetailContract {
    data class BreedDetailUiState(
        val loading: Boolean = false,
        val breedId: String,
        val data: BreedDetailUIModel? = null,
    )
    /**
     * Events that can be triggered in the BreedDetail screen
     * Currently empty.
     * */
    sealed class BreedDetailUiEvent {
    }
}