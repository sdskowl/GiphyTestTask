package com.giphytesttask.ui.common

import android.os.Build
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.giphytesttask.data.models.GifUi

@Composable
fun GifItemSmall(modifier: Modifier = Modifier, item: GifUi, onClick: (String) -> Unit = {}) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context).crossfade(true).data(item.smallUrl).decoderFactory(
        if (Build.VERSION.SDK_INT >= 28) {
            ImageDecoderDecoder.Factory()
        } else {
            GifDecoder.Factory()
        }
    ).build()

    SubcomposeAsyncImage(
        modifier = modifier.clickable { onClick(item.id) },
        model = request,
        contentDescription = null,
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent()
            }

            AsyncImagePainter.State.Empty -> Text(text = "empty")
            is AsyncImagePainter.State.Error -> Text(text = "error")
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
        }

    }
}

@Preview
@Composable
fun GifItemSmallPreview() {
    val item =
        GifUi(smallUrl = "https://media0.giphy.com/media/naiatn5LxTOsU/giphy.gif?cid=915a99aarwuwyr08wkv8l7ktdfcmqpulggigew13kmqeistc&ep=v1_gifs_search&rid=giphy.gif&ct=g")
    GifItemSmall(item = item)
}

@Composable
fun GifItemOriginal(modifier: Modifier = Modifier, item: GifUi, onClick: (String) -> Unit = {}) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context).crossfade(true).data(item.smallUrl).decoderFactory(
        if (Build.VERSION.SDK_INT >= 28) {
            ImageDecoderDecoder.Factory()
        } else {
            GifDecoder.Factory()
        }
    ).build()

    SubcomposeAsyncImage(
        modifier = modifier.clickable { onClick(item.id) },
        model = request,
        contentDescription = null,
        contentScale = ContentScale.Fit
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent()
            }

            AsyncImagePainter.State.Empty -> Text(text = "empty")
            is AsyncImagePainter.State.Error -> Text(text = "error")
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
        }

    }
}

@Preview
@Composable
fun GifItemOriginalPreview() {
    val item =
        GifUi(originalUrl = "https://media0.giphy.com/media/naiatn5LxTOsU/giphy.gif?cid=915a99aarwuwyr08wkv8l7ktdfcmqpulggigew13kmqeistc&ep=v1_gifs_search&rid=giphy.gif&ct=g")
    GifItemOriginal(item = item)
}
