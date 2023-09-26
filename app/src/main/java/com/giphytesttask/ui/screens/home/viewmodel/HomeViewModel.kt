package com.giphytesttask.ui.screens.home.viewmodel

import com.giphytesttask.base.BaseViewModel
import com.giphytesttask.data.GifRepository
import com.giphytesttask.data.GifRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gifRepository: GifRepository) :
    BaseViewModel<HomeState, HomeEvent, HomeEffect>(HomeState()) {
    private val TAG = javaClass.simpleName

    init {
        subscribeToEvents()
    }

    override suspend fun onEvent(event: HomeEvent) {
        when (event){
            is OnSearch -> {
                update { state -> state.copy(isLoading = true) }
                val result = gifRepository.search(event.value)
                update { state -> state.copy(gifList = result, isLoading = false) }
            }
        }
    }
}