package com.syhan.cinemasearch.core.presentation.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.theme.imageBackgroundColor
import com.syhan.cinemasearch.core.presentation.theme.imageContentColor
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieCard(
    name: String,
    imageUrl: String?,
) {
    val painter = rememberAsyncImagePainter(imageUrl)
    val painterState by painter.state.collectAsStateWithLifecycle()
    val showShimmer = painterState is AsyncImagePainter.State.Loading

    Column {
        AsyncImage(
            model = painter,
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_media),
            fallback = painterResource(R.drawable.ic_media),
            colorFilter = ColorFilter.tint(color = imageContentColor),
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .background(
                    color = imageBackgroundColor,
                    shape = RoundedCornerShape(4.dp)
                )
                .width(160.dp)
                .height(270.dp)
                .clip(RoundedCornerShape(4.dp))
                .then(
                    if (showShimmer) {
                        Modifier.shimmer()
                    } else Modifier
                )
        )
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(160.dp)
        )
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    Surface(Modifier.fillMaxSize()) {
        MovieCard(
            name = "Movie name that is intentionally very long",
            imageUrl = null
        )
    }
}