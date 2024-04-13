package raf.ddjuretanovic8622rn.catalist.cats.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import raf.ddjuretanovic8622rn.catalist.cats.api.model.BreedApiModel
import raf.ddjuretanovic8622rn.catalist.cats.api.model.ImageApiModel
import raf.ddjuretanovic8622rn.catalist.cats.detail.BreedDetailContract.BreedDetailUiEvent
import raf.ddjuretanovic8622rn.catalist.cats.detail.BreedDetailContract.BreedDetailUiState
import raf.ddjuretanovic8622rn.catalist.cats.detail.model.ImageUIModel
import raf.ddjuretanovic8622rn.catalist.cats.repository.CatRepository
import kotlin.time.Duration.Companion.milliseconds
import raf.ddjuretanovic8622rn.catalist.cats.detail.model.BreedDetailUIModel as BreedDetailUIModel1

private const val TAG = "BreedDetailViewModel"

class BreedDetailViewModel(
    private val breedId: String,
    private val repository: CatRepository = CatRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(BreedDetailUiState(breedId = breedId))
    val state = _state.asStateFlow()
    private fun setState(reducer: BreedDetailUiState.() -> BreedDetailUiState) =
        _state.getAndUpdate(reducer)

    private val events = MutableSharedFlow<BreedDetailUiEvent>()
    fun setEvent(event: BreedDetailUiEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        observeEvents()
        fetchBreedDetails()
    }

    private fun fetchBreedDetails() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            delay(250.0.milliseconds)
            try {
                val breed = repository.getBreed(breedId)
                val ui = breed.asBreedDetailUiModel()
                setState { copy(data = ui) }
            } catch (e: Exception) {
                Log.e(TAG, "fetchBreedDetails: $e")
            } finally {
                setState { copy(loading = false) }
            }
        }
    }

    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    else -> {}
                }
            }
        }
    }


    private suspend fun BreedApiModel.asBreedDetailUiModel(): BreedDetailUIModel1 {
        val images: List<ImageApiModel> = repository.getImages(breedId)
        val ui = BreedDetailUIModel1(
            id = this.breedId,
            name = this.name,
            description = this.description,
            temperament = this.temperament.split(", "),
            // Does nothing, as technically all fo the country_code entries only have one country,
            // for now...
            countriesOfOrigin = this.countryCodes.split(" "),
            lifeSpan = lifeSpan,
            averageWeight = this.weight.metric,
            adaptability = this.adaptability,
            affectionLevel = this.affectionLevel,
            childFriendly = this.childFriendly,
            dogFriendly = this.dogFriendly,
            energyLevel = this.energyLevel,
            grooming = this.grooming,
            healthIssues = this.healthIssues,
            intelligence = this.intelligence,
            sheddingLevel = this.sheddingLevel,
            socialNeeds = this.socialNeeds,
            strangerFriendly = this.strangerFriendly,
            vocalisation = this.vocalisation,
            isRare = this.rare == 1,
            wikipediaUrl = this.wikipediaUrl,
            images = images.map { it.asImageUiModel() }
        )
        return ui
    }

    private fun ImageApiModel.asImageUiModel() = ImageUIModel(
        id = this.id,
        url = this.url,
        width = this.width,
        height = this.height
    )
}