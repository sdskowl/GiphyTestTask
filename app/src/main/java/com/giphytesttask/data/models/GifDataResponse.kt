package com.giphytesttask.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifDataResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "images")
    val images: ImagesResponse? = null,
)