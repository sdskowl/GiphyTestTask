package com.giphytesttask.ui.screens.gifdetail.viewmodel

import com.giphytesttask.base.UiEvent


sealed interface GifDetailEvent : UiEvent
data object FetchData : GifDetailEvent