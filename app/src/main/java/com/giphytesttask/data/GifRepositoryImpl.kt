package com.giphytesttask.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.giphytesttask.base.RepoResult
import com.giphytesttask.base.handleApi
import com.giphytesttask.base.mapToRepo
import com.giphytesttask.data.models.ErrorUi
import com.giphytesttask.data.models.GifDataResponse
import com.giphytesttask.data.models.GifUi
import com.giphytesttask.data.network.GiphyApiService
import com.giphytesttask.data.paging.GifPagingSource
import com.giphytesttask.di.MyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GifRepositoryImpl(
    private val dispatchers: MyDispatchers,
    private val gifApiService: GiphyApiService,
    private val mapper: Mapper
) : GifRepository {
    override suspend fun search(query: String): Flow<PagingData<GifUi>> {
        return withContext(dispatchers.io) {
            Pager(
                PagingConfig(pageSize = 25)
            ) {
                GifPagingSource(gifApiService = gifApiService, query = query)
            }
        }.flow.map { paging -> paging.map { item: GifDataResponse -> mapper.mapToUi(item) } }
    }

    override suspend fun getById(id: String): RepoResult<GifUi, ErrorUi> {
        return withContext(dispatchers.io) {
            handleApi { gifApiService.getById(id = id) }.mapToRepo(successMap = {
                mapper.mapToUi(it.data.data)
            }, errorMap = { mapper.mapToUiError(it.body) })

        }
    }

}