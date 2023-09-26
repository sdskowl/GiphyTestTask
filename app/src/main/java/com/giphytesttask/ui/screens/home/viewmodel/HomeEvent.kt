package com.giphytesttask.ui.screens.home.viewmodel

import com.giphytesttask.base.UiEvent


sealed interface HomeEvent : UiEvent
data class OnSearch(val value: String) : HomeEvent