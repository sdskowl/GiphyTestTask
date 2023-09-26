package com.giphytesttask.ui.screens.gifdetail.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.giphytesttask.base.BaseViewModel
import com.giphytesttask.base.onError
import com.giphytesttask.base.onException
import com.giphytesttask.base.onSuccess
import com.giphytesttask.data.GifRepository
import com.giphytesttask.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val gifRepository: GifRepository
) :
    BaseViewModel<GifDetailState, GifDetailEvent, GifDetailEffect>(GifDetailState()) {
    private val TAG = javaClass.simpleName

    init {
        subscribeToEvents()
        sendEvent(FetchData)
    }

    override suspend fun onEvent(event: GifDetailEvent) {
        when (event) {
            FetchData -> {
                update { state -> state.copy(isLoading = true) }
                val gifId = savedStateHandle[Screen.GifDetail.gifid] ?: ""

                gifRepository.getById(gifId).onSuccess {
                    Log.d(TAG, "gif $it")
                    update { state ->  state.copy(isLoading = false, gif = it)}
                }.onError {
                    update { state ->  state.copy(isLoading = false)}
                }.onException {
                    update { state ->  state.copy(isLoading = false)}
                }
            }
        }
    }
}