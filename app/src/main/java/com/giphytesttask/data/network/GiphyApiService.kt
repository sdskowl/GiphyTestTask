package com.giphytesttask.data.network

import com.giphytesttask.BuildConfig
import com.giphytesttask.data.models.GiphyByIdResponse
import com.giphytesttask.data.models.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyApiService {

    @GET("search")
    suspend fun search(
        @Query("api_key", encoded = true) api: String = BuildConfig.API_KEY,
        @Query("q", encoded = true) q: String,
        @Query("limit", encoded = true) limit: Int,
        @Query("offset", encoded = true) offset: Int,
        @Query("rating", encoded = true) rating: String = "g",
        @Query("lang", encoded = true) lang: String = "en"
    ): Response<GiphyResponse>

    @GET("{id}")
    suspend fun getById(
        @Path("id") id: String,
        @Query(
            "api_key",
            encoded = true
        ) api: String = BuildConfig.API_KEY,
    ): Response<GiphyByIdResponse>
}