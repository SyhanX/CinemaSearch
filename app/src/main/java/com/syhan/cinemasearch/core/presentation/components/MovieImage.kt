package com.syhan.cinemasearch.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.theme.imageBackgroundColor
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    onStart: () -> Unit = {},
    onError: () -> Unit = {},
    onSuccess: () -> Unit = {},
) {
    var showShimmer by remember { mutableStateOf(true) }
    var contentScale by remember { mutableStateOf<ContentScale>(ContentScale.Crop) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.ic_media)
            .listener(
                onStart = {
                    showShimmer = true
                    contentScale = ContentScale.Crop
                    onStart()
                },
                onError = { _, _ ->
                    showShimmer = false
                    contentScale = ContentScale.Inside
                    onError()
                },
                onSuccess = { _, _ ->
                    showShimmer = false
                    contentScale = ContentScale.Crop
                    onSuccess()
                }
            )
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .background(
                color = imageBackgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clip(RoundedCornerShape(4.dp))
            .then(
                if (showShimmer) Modifier.shimmer() else Modifier
            )
    )
}