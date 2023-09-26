package com.giphytesttask.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = "original")
    val original: OriginalResponse? = null,
    @Json(name = "preview_gif")
    val previewGif: PreviewGifResponse? = null,
)