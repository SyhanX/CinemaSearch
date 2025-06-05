package com.syhan.cinemasearch.core.presentation.movie_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.core.presentation.components.MovieImage
import com.syhan.cinemasearch.core.presentation.theme.white

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String?,
    onClick: () -> Unit,
) {
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
            MovieImage(
                modifier = Modifier
                    .width(160.dp)
                    .height(222.dp),
                imageUrl = imageUrl,
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