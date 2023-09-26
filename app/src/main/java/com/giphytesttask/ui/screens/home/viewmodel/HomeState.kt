package com.giphytesttask.ui.screens.home.viewmodel

import androidx.paging.PagingData
import com.giphytesttask.base.UiState
import com.giphytesttask.data.models.GifUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


data class HomeState(
    val isLoading: Boolean = false,
    val gifList: Flow<PagingData<GifUi>> = flowOf(PagingData.empty())
) : UiState