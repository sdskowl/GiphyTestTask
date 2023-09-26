package com.giphytesttask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.giphytesttask.base.ApiError
import com.giphytesttask.base.ApiException
import com.giphytesttask.base.ApiSuccess
import com.giphytesttask.base.handleApi
import com.giphytesttask.data.models.GifDataResponse
import com.giphytesttask.data.network.GiphyApiService

class GifPagingSource(private val gifApiService: GiphyApiService, private val query: String) : PagingSource<Int, GifDataResponse>() {
    override fun getRefreshKey(state: PagingState<Int, GifDataResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifDataResponse> {
        val nextPage = params.key ?: 0
        val response = handleApi {
            gifApiService.search(q = query, offset = nextPage * 25, limit = 25)
        }

        return when (response) {
            is ApiError -> LoadResult.Error(Exception())
            is ApiException -> LoadResult.Error(response.e)
            is ApiSuccess -> {
                val list = response.data.data?.filterNotNull() ?: emptyList()
                LoadResult.Page(
                    data = list,
                    prevKey = if (nextPage == 0) null else (nextPage - 1).coerceAtLeast(0),
                    nextKey = if (list.isEmpty()) null else nextPage + 1
                )
            }
        }
    }
}