package com.giphytesttask.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.giphytesttask.R
import com.giphytesttask.data.models.GifUi
import com.giphytesttask.ui.common.GifItemSmall
import com.giphytesttask.ui.screens.home.viewmodel.HomeState
import com.giphytesttask.ui.theme.GiphyTestTaskTheme
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun HomeContent(
    state: HomeState = HomeState(),
    invokes: HomeScreenInvokes = HomeScreenInvokes(),
    navigation: HomeScreenNavigation = HomeScreenNavigation()
) {
    val items = state.gifList.collectAsLazyPagingItems()
    val stateGrid = rememberLazyGridState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var text by rememberSaveable {
            mutableStateOf("")
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            keyboardActions = KeyboardActions(onSearch = {
                invokes.onSearch(text)
            }), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
        if (items.itemCount > 0) {
            LazyVerticalGrid(
                state = stateGrid,
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(count = items.itemCount) { index ->
                    val item = items[index] ?: return@items
                    GifItemSmall(
                        item = item,
                        modifier = Modifier.size(200.dp),
                        onClick = navigation.onDetail
                    )
                }

            }

        }
        AnimatedVisibility(items.loadState.refresh is LoadState.Error) {
            Text(text = stringResource(id = R.string.someWrong))
        }
    }

}


@Preview
@Composable
internal fun HomeContentPreview() {

    GiphyTestTaskTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val state = HomeState(gifList = flowOf(PagingData.from(List(15) {
                GifUi(smallUrl = "https://media0.giphy.com/media/naiatn5LxTOsU/giphy.gif?cid=915a99aarwuwyr08wkv8l7ktdfcmqpulggigew13kmqeistc&ep=v1_gifs_search&rid=giphy.gif&ct=g")
            })))
            HomeContent(state = state)
        }
    }
}