package com.giphytesttask.data

import androidx.paging.PagingData
import com.giphytesttask.base.RepoResult
import com.giphytesttask.data.models.ErrorUi
import com.giphytesttask.data.models.GifUi
import kotlinx.coroutines.flow.Flow

interface GifRepository {
    suspend fun search(query: String): Flow<PagingData<GifUi>>
    suspend fun getById(id: String): RepoResult<GifUi, ErrorUi>
}