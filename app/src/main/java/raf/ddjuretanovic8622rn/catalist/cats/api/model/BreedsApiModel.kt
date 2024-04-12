package raf.ddjuretanovic8622rn.catalist.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The model for a list of breeds.
 * GET /breeds
 *
 * */
@Serializable
data class BreedsApiModel (
    @SerialName("id") val breedId: String,
    val name: String,
    val altNames: String = "",
    val description: String,
    val temperament: String,
)


/**
 * The model for a breed.
 * GET /breeds/{breedId}
 *
 * Only contains fields returned by the API, even though the actual implementation will require many more.
 * */
@Serializable
data class BreedApiModel (
    val name: String,
    val weight: String,
    val height: String,
    val lifeSpan: String,
)