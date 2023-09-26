package com.giphytesttask.ui.screens.gifdetail.viewmodel

import com.giphytesttask.base.UiState
import com.giphytesttask.data.models.GifUi


data class GifDetailState(
    val isLoading: Boolean = true,
    val gif: GifUi = GifUi()
) : UiState