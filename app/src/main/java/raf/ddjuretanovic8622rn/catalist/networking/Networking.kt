package raf.ddjuretanovic8622rn.catalist.networking

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val AppJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
}

val okHttpClient= OkHttpClient.Builder()
    .addInterceptor {
        val request = it.request().newBuilder()
            .addHeader("x-api-key", "live_uD7Zbg43O1QGbZ82cKzRuBNAMLIiDU7ieKA5M0NdudabP98rGTxkmo7TH5qyHkJG")
            .build()
        it.proceed(request)
    }
    .build()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.thecatapi.com/v1/")
    .client(okHttpClient)
    .addConverterFactory(AppJson.asConverterFactory("application/json".toMediaType()))
    .build()