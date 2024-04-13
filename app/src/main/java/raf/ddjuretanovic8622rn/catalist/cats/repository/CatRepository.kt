package raf.ddjuretanovic8622rn.catalist.cats.repository

import raf.ddjuretanovic8622rn.catalist.cats.api.CatsApi
import raf.ddjuretanovic8622rn.catalist.networking.retrofit

object CatRepository {

    private val catsApi: CatsApi = retrofit.create(CatsApi::class.java)

    suspend fun getBreeds() = catsApi.getBreeds()

    suspend fun getBreed(breedId: String) = catsApi.getBreed(breedId)

    suspend fun getImages(breedIds: String) = catsApi.getImages(breedIds)
}