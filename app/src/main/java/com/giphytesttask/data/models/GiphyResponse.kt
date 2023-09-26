package com.giphytesttask.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyResponse(
    @Json(name = "data")
    val `data`: List<GifDataResponse?>? = null,
)