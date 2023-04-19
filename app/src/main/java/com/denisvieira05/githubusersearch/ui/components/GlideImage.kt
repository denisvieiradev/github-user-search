package com.denisvieira05.githubusersearch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun GlideImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    loadingSize: Dp,
    loading: @Composable () -> Unit = { CircularProgressLoading(size = loadingSize) },
    error: @Composable ((Throwable) -> Unit)? = null
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )


    when (painter.state) {
        is AsyncImagePainter.State.Loading -> loading()
        is AsyncImagePainter.State.Error -> {
//            error?.invoke(imageLoadState.request.)
        }
        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = modifier
            )
        }
        else -> {}
    }
}
