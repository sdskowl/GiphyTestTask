package com.giphytesttask.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreviewGifResponse(
    @Json(name = "height")
    val height: String? = null,
    @Json(name = "size")
    val size: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "width")
    val width: String? = null
)