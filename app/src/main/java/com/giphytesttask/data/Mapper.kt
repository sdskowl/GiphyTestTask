package com.giphytesttask.data

import com.giphytesttask.data.models.ErrorUi
import com.giphytesttask.data.models.GifDataResponse
import com.giphytesttask.data.models.GifUi
import okhttp3.ResponseBody

class Mapper {
    fun mapToUi(value: GifDataResponse?): GifUi {
        return GifUi(
            id = value?.id ?: "",
            smallUrl = value?.images?.previewGif?.url ?: "",
            originalUrl = value?.images?.original?.url ?: ""
        )
    }
    fun mapToUiError(value: ResponseBody?): ErrorUi = ErrorUi(message = value.toString())
}