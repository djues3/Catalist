package raf.ddjuretanovic8622rn.catalist.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The model for a list of breeds.
 * GET /breeds
 *
 * */

/*
* {
    "weight": {
      "imperial": "6 - 10",
      "metric": "3 - 5"
    },
    "id": "lape",
    "name": "LaPerm",
    "cfa_url": "http://cfa.org/Breeds/BreedsKthruR/LaPerm.aspx",
    "vetstreet_url": "http://www.vetstreet.com/cats/laperm",
    "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/laperm",
    "temperament": "Affectionate, Friendly, Gentle, Intelligent, Playful, Quiet",
    "origin": "Thailand",
    "country_codes": "TH",
    "country_code": "TH",
    "description": "LaPerms are gentle and affectionate but also very active. Unlike many active breeds, the LaPerm is also quite content to be a lap cat. The LaPerm will often follow your lead; that is, if they are busy playing and you decide to sit and relax, simply pick up your LaPerm and sit down with it, and it will stay in your lap, devouring the attention you give it.",
    "life_span": "10 - 15",
    "indoor": 0,
    "lap": 1,
    "alt_names": "Si-Sawat",
    "adaptability": 5,
    "affection_level": 5,
    "child_friendly": 4,
    "dog_friendly": 5,
    "energy_level": 4,
    "grooming": 1,
    "health_issues": 1,
    "intelligence": 5,
    "shedding_level": 3,
    "social_needs": 4,
    "stranger_friendly": 4,
    "vocalisation": 3,
    "experimental": 0,
    "hairless": 0,
    "natural": 0,
    "rare": 0,
    "rex": 1,
    "suppressed_tail": 0,
    "short_legs": 0,
    "wikipedia_url": "https://en.wikipedia.org/wiki/LaPerm",
    "hypoallergenic": 1,
    "reference_image_id": "aKbsEYjSl",
    "image": {
      "id": "aKbsEYjSl",
      "width": 1074,
      "height": 890,
      "url": "https://cdn2.thecatapi.com/images/aKbsEYjSl.jpg"
    }
  }
* */

@Serializable
data class BreedsApiModel (
    val weight: WeightApiModel,
    @SerialName("id") val breedId: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val countryCodes: String = "",
    val countryCode: String = "",
    val description: String,
    val lifeSpan: String = "",
    val indoor: Int = 0,
    val lap: Int = 0,
    @SerialName("alt_names") val altNames: String = "",
    val adaptability: Int = 0,
    val affectionLevel: Int = 0,
    val childFriendly: Int = 0,
    val dogFriendly: Int = 0,
    val energyLevel: Int = 0,
    val grooming: Int = 0,
    val healthIssues: Int = 0,
    val intelligence: Int = 0,
    val sheddingLevel: Int = 0,
    val socialNeeds: Int = 0,
    val strangerFriendly: Int = 0,
    val vocalisation: Int = 0,
    val experimental: Int = 0,
    val hairless: Int = 0,
    val natural: Int = 0,
    val rare: Int = 0,
    val rex: Int = 0,
    val suppressedTail: Int = 0,
    val shortLegs: Int = 0,
    @SerialName("wikipedia_url") val wikipediaUrl: String = "",
    val hypoallergenic: Int = 0,
    @SerialName("reference_image_id") val referenceImageId: String = "",
    val image: ImageApiModel? = null,

)
@Serializable
data class WeightApiModel(
    val imperial: String,
    val metric: String,
)
@Serializable
data class ImageApiModel(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
)
