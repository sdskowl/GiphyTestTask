package com.giphytesttask.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaResponse(
    @Json(name = "msg")
    val msg: String? = null,
    @Json(name = "response_id")
    val responseId: String? = null,
    @Json(name = "status")
    val status: Int? = null
)