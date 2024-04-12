package raf.ddjuretanovic8622rn.catalist.cats.api

import raf.ddjuretanovic8622rn.catalist.cats.api.model.BreedApiModel
import raf.ddjuretanovic8622rn.catalist.cats.api.model.BreedsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CatsApi {

    @GET("breeds")
    suspend fun getBreeds(): List<BreedsApiModel>

    @GET("breeds/{breedId}")
    suspend fun getBreed(
        @Path("breedId") breedId: String,
    ): BreedApiModel

    @GET("breeds/search?q={breedName}")
    suspend fun getBreedByName(
        @Path("breedName") breedName: String,
    ): List<BreedsApiModel>
}