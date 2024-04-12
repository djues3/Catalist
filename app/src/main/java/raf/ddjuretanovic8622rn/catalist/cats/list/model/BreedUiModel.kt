package raf.ddjuretanovic8622rn.catalist.cats.list.model

data class BreedUiModel(
    val id: String,
    val name: String,
    val altNames: List<String>,
    val description: String,
    val temperament: List<String>,
)
