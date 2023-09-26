package com.giphytesttask.data.models

import com.squareup.moshi.Json

data class GiphyByIdResponse(
    @Json(name = "data")
    val `data`: GifDataResponse? = null,
)
