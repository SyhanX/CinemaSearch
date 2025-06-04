package com.syhan.cinemasearch.core.presentation.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.theme.imageBackgroundColor
import com.syhan.cinemasearch.core.presentation.theme.white
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String?,
    onClick: () -> Unit,
) {
    var showShimmer by remember { mutableStateOf(false) }
    var contentScale by remember { mutableStateOf<ContentScale>(ContentScale.Crop) }

    Surface(
        onClick = onClick,
        color = white,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(160.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .error(R.drawable.ic_media)
                    .listener(
                        onStart = {
                            contentScale = ContentScale.Crop
                            showShimmer = true
                        },
                        onError = { _, _ ->
                            contentScale = ContentScale.Inside
                            showShimmer = false
                        },
                        onSuccess = { _, _ ->
                            contentScale = ContentScale.Crop
                            showShimmer = false
                        }
                    )
                    .build(),
                contentDescription = null,
                contentScale = contentScale,
                modifier = Modifier
                       .background(
                           color = imageBackgroundColor,
                           shape = RoundedCornerShape(4.dp)
                       )
                    .width(160.dp)
                    .height(222.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .then(
                        if (showShimmer) {
                            Modifier.shimmer()
                        } else Modifier
                    )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = TextUnit(1.4f, TextUnitType.Em),
                modifier = Modifier
                    .width(160.dp)
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    Surface(Modifier.fillMaxSize()) {
        MovieItem(
            name = "Movie name that is intentionally very very very long",
            imageUrl = null,
            onClick = {}
        )
    }
}