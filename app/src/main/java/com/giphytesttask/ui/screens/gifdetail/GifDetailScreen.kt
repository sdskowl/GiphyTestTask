package com.giphytesttask.ui.screens.gifdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.giphytesttask.ui.screens.gifdetail.viewmodel.GifDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun GifDetailScreen(vm: GifDetailViewModel, navigation: GifDetailScreenNavigation) {
    val state by vm.state.collectAsStateWithLifecycle()
    val invokes = GifDetailScreenInvokes()
    LaunchedEffect(Unit) {
        vm.effect.onEach { effect ->
            when (effect) {
                else -> {}
            }
        }.collect()
    }
    GifDetailContent(state = state, invokes = invokes, navigation = navigation)
}

internal data class GifDetailScreenInvokes(
    val onClick: () -> Unit = {},
)

data class GifDetailScreenNavigation(
    val onBack: () -> Unit = {},
)