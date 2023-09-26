package com.giphytesttask.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.giphytesttask.ui.screens.home.viewmodel.HomeViewModel
import com.giphytesttask.ui.screens.home.viewmodel.OnSearch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(vm: HomeViewModel, navigation: HomeScreenNavigation) {
    val state by vm.state.collectAsStateWithLifecycle()
    val invokes = HomeScreenInvokes(onSearch = {vm.sendEvent(OnSearch(it))})
    LaunchedEffect(Unit) {
        vm.effect.onEach { effect ->
            when (effect) {
                else -> {}
            }
        }.collect()
    }
    HomeContent(state = state, invokes = invokes, navigation = navigation)
}

internal data class HomeScreenInvokes(
    val onSearch: (String) -> Unit = {},
)

data class HomeScreenNavigation(
    val onDetail: (String) -> Unit = {},
)