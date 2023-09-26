package com.giphytesttask.data.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    @Json(name = "count")
    val count: Int? = null,
    @Json(name = "offset")
    val offset: Int? = null,
    @Json(name = "total_count")
    val totalCount: Int? = null
)