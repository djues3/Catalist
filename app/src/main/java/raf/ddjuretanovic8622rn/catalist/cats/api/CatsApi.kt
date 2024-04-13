package raf.ddjuretanovic8622rn.catalist.cats.api

import raf.ddjuretanovic8622rn.catalist.cats.api.model.BreedApiModel
import raf.ddjuretanovic8622rn.catalist.cats.api.model.ImageApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatsApi {

    @GET("breeds")
    suspend fun getBreeds(): List<BreedApiModel>

    @GET("breeds/{breedId}")
    suspend fun getBreed(
        @Path("breedId") breedId: String,
    ): BreedApiModel
    /**
     * Returns a list of images for a specific breed.
     * */
    @GET("images/search")
    suspend fun getImages(
        @Query("breed_ids") breedIds: String,
        @Query("limit") limit: Int = 10,
    ): List<ImageApiModel>

}